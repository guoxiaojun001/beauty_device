package com.machine.manager.controller;


import com.machine.manager.jwt.RestResult;
import com.machine.manager.mqtt.MqttGateway;
import com.machine.manager.reject.UserLoginToken;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * mqtt controller
 *
 * @author guoxi_789@126.com
 * @date 2020/12/15
 */
@RestController
@Api("消息推送")
@RequestMapping("/mqtt")
public class MessageController extends BaseController {
    @Autowired
    MqttGateway mqttGateway;

    /***
     * 发布消息，用于其他客户端消息接收测试
     */

    @ResponseBody
    @UserLoginToken
    @PostMapping("/sendMqttMessage")
    public RestResult sendMqttMessage( String message, String topic) {
        RestResult pre = preCheck("sendMqttMessage",MessageController.class);
        if(null != pre){
            System.out.print("提前判断权限，权限失败");
            return pre;
        }else {
            mqttGateway.sendToMqtt(message, topic);
            RestResult restResult = new RestResult();
            restResult.setSuccess(true);
            restResult.setMsg("发送成功");
            restResult.setCode(200);
            return restResult;
        }

    }



    @ResponseBody
    @PostMapping("/sendMQ")
    public RestResult sendMQ(@RequestBody String data){
        RestResult pre = preCheck("sendMQ",MessageController.class);
        if(null != pre){
            System.out.print("提前判断权限，权限失败");
            return pre;
        }else {
            mqttGateway.sendToMqtt(data, "topic_req");

            RestResult restResult = new RestResult();
            restResult.setCode(200);
            restResult.setMsg("发送成功");
            restResult.setSuccess(true);
            return restResult;
        }

    }
}