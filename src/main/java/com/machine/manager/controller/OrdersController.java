package com.machine.manager.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.entity.Order;
import com.machine.manager.entity.Store;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.jwt.JwtTokenUtil222;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.reject.AdminToken;
import com.machine.manager.reject.UserLoginToken;
import com.machine.manager.service.OrderService;
import com.machine.manager.service.StoreService;
import com.machine.manager.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 门店操作
 *
 */
@RestController
@Api("订单操作")
@RequestMapping("/order")
public class OrdersController extends  BaseController{
    @Autowired
    private OrderService orderService;

    @ApiOperation("新增门店")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/addOrder")
    public RestResult addOrder(@RequestBody Order order) {
        RestResult restResult = new RestResult();
        int code = orderService.insertSelective(order);
        if(code == 1){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("添加成功");
        }else {
            restResult.setCode(203);
            restResult.setSuccess(false);
            restResult.setMsg("添加失败");
        }

        return restResult;
    }

    @ApiOperation("删除订单")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/deleteOrder")
    public RestResult deleteOrderById(Integer id) {
        RestResult restResult = new RestResult();
        int code = orderService.deleteByPrimaryKey(id);
        if(code == 1){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("删除成功");
        }else {
            restResult.setCode(203);
            restResult.setSuccess(false);
            restResult.setMsg("删除失败");
        }
        return restResult;
    }

    @ApiOperation("修改订单")
    @UserLoginToken
//        @AdminToken
    @PostMapping("/updateOrder")
    public RestResult updateStoreInfo(@RequestBody Order order) {
        RestResult restResult = new RestResult();
        int code = orderService.updateByPrimaryKeySelective(order);
        if(code == 1){
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("更新成功");
        }else {
            restResult.setCode(203);
            restResult.setSuccess(false);
            restResult.setMsg("更新失败");
        }

        return restResult;
    }


//    @ApiOperation("通过id查询订单")
//    @UserLoginToken
////    @AdminToken
//    @PostMapping("/queryByOrderId")
//    public RestResult queryByOrderId(Integer id) {
//        System.out.print("  queryByOrderId :" + id);
//        RestResult restResult = new RestResult();
//        Order order = orderService.selectByPrimaryKey(id);
//        restResult.setCode(200);
//        restResult.setData(order);
//        restResult.setMsg("查询成功");
//        restResult.setSuccess(true);
//
//        return restResult;
//    }


    @ApiOperation("通过订单号查询订单")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/queryByOrderNo")
    public RestResult queryByOrderNo(String orderNo) {
        System.out.print("  queryByOrderNo :" + orderNo);
        RestResult restResult = new RestResult();
        Order order = orderService.selectByOrderNo(orderNo);
        restResult.setCode(200);
        restResult.setData(order);
        restResult.setMsg("查询成功");
        restResult.setSuccess(true);

        return restResult;
    }

    @ApiOperation("查询订单列表 ")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/queryAllOrder")
    public RestResult queryAllOrder() {
        //TODO 涉及多表关联查询
        RestResult restResult = new RestResult();
        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        if(null == httpServletRequest ){
            restResult.setCode(200);
            restResult.setData("no data");
            restResult.setMsg("请求参数异常2");
            restResult.setSuccess(false);
            return restResult;
        }

        String token = httpServletRequest.getHeader("token");
        if(null == token || "".equals(token)){
            restResult.setCode(200);
            restResult.setSuccess(false);
            restResult.setMsg("请求token为空");
            return restResult;
        }

        String userType = "user";
        int userId = -1;
        List<Order> list ;

        try {
            DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
            userId = decodedJWT.getClaim("userId").asInt();
            userType = decodedJWT.getClaim("userType").asString();

            logger.info("userId , userType {} -- {} ", userId,userType);
        } catch ( Exception e) {
        }

        if("admin".equals(userType)){
            list = orderService.selectAll();
            restResult.setData(list);
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("查询成功");
        }else {
            list = orderService.selectCurrentUser(userId );
            restResult.setData(list);
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("查询成功2");
        }

        return restResult;
    }
}
