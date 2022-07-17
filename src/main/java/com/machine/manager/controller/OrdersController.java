package com.machine.manager.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.entity.*;
import com.machine.manager.entity.machine.CommonRequest;
import com.machine.manager.jwt.JwtTokenUtil222;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.reject.AdminToken;
import com.machine.manager.reject.UserLoginToken;
import com.machine.manager.service.MachineService;
import com.machine.manager.service.OrderService;
import com.machine.manager.service.StoreService;
import com.machine.manager.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    private MachineService service;

    @ApiOperation("新增订单,订单状态1 ：待支付，2： 已经支付，3 取消")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/addOrder")
    public RestResult addOrder(@RequestBody Order order) {
        RestResult restResult = new RestResult();

        logger.info( "新增订单 add order => " + order.toString());
        order.setOrderNo("orderNo-" + UUID.randomUUID());
        Date date = new Timestamp(System.currentTimeMillis());
        System.out.println("创建时间 ： " + date);
        order.setCreateTime(date.toString());
        order.setOrderStatus(1);
        order.setPayStatus(1);
        int price = order.getPrice();
        System.out.println("price ： " + price);

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
        logger.info( "修改订单状态 add order => " + order.toString());

        int status = order.getPayStatus();
        int price = order.getPrice();

        if(status == 2){
            //更新设备 剩余工作时间
            MachineInfo machineInfo = service.selectDeviceId(order.getMachineParam());
            if(machineInfo != null){
                System.out.println("更新设备 剩余工作时间 ： " );
                int leftTime = machineInfo.getLeftTime();
                leftTime += (price * 45 * 60);
                machineInfo.setLeftTime(leftTime);
                service.updateByPrimaryKey(machineInfo);
            }
        }else if(status == 3){
            //删除订单
            System.out.println("更新设备 剩余工作时间 ： " );
            orderService.deleteByPrimaryKey(order.getId());
        }

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


/*    @ApiOperation("查询订单")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/queryByOrder")
    public RestResult queryByOrder(String parms) {

        HttpServletRequest httpServletRequest = RequestUtils.getHttpRequest();
        RestResult restResult= new RestResult();
        if(null == httpServletRequest ){
            restResult.setCode(202);
            restResult.setData("no data");
            restResult.setMsg("请求参数异常3");
            restResult.setSuccess(false);
            return restResult;
        }

        String token = httpServletRequest.getHeader("token");
        if( StringUtils.isEmpty(token)){
            restResult.setCode(202);
            restResult.setSuccess(false);
            restResult.setMsg("请求token为空3");
        }else {
            try {
                DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
                int userId = decodedJWT.getClaim("userId").asInt();
                String userType = decodedJWT.getClaim("userType").asString();

                if(!StringUtils.isEmpty(userType) && "admin".equals(userType)){
                    System.out.print(" 管理员查询 queryByOrder :" + parms);
                    Order order = orderService.selectByOrderNo(parms);
                    restResult.setCode(200);
                    restResult.setData(order);
                    restResult.setMsg("查询成功");
                    restResult.setSuccess(true);
                }else {
                    System.out.print(" 不是管理员查询，需要添加门店号， queryByOrderNo :" + parms);
                }
            }catch (Exception e){
            }
        }


        return restResult;
    }*/

    @ApiOperation("查询订单列表 ,参数传空 返回所有")
    @UserLoginToken
//    @AdminToken
    @PostMapping("/queryOrderList")
    public RestResult queryAllOrder(@RequestBody CommonRequest commonRequest) {
        //TODO 涉及多表关联查询
        logger.info("queryOrderList commonRequest = " + commonRequest);
        RestResult restResult = new RestResult();
                List<Order> list ;
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


        try {
            DecodedJWT decodedJWT = JwtTokenUtil222.getTokenInfo(token);
            userId = decodedJWT.getClaim("userId").asInt();
            userType = decodedJWT.getClaim("userType").asString();

            logger.info("userId , userType {} -- {} ", userId,userType);
        } catch ( Exception e) {
        }

        int page =commonRequest.getPage();
        int pageSize = commonRequest.getPageSize();
        int pageIndex =(page-1) * pageSize;
        if("admin".equals(userType)){
            int total = orderService.selectAllByAgentId(null).size();
            list = orderService.selectCurrentUser(null, commonRequest.getParms(),pageIndex,pageSize);
            restResult.setData(list);
            restResult.setCounts(total);
            restResult.setCode(200);
            restResult.setSuccess(true);
            restResult.setMsg("查询成功");
        }else {
            int total = orderService.selectAllByAgentId(userId).size();
            list = orderService.selectCurrentUser(userId ,commonRequest.getParms(),pageIndex,pageSize);
            restResult.setData(list);
            restResult.setCode(200);
            restResult.setCounts(total);
            restResult.setSuccess(true);
            restResult.setMsg("查询成功2");
        }

        return restResult;
    }
}
