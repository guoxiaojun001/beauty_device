package com.machine.manager.controller;


import com.machine.manager.jwt.RestResult;
import com.machine.manager.mqtt.MqttGateway;
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
    @PostMapping("/sendMqttMessage")
    public RestResult sendMqttMessage(String message, String topic) {
        mqttGateway.sendToMqtt(message, topic);
        RestResult restResult = new RestResult();
        restResult.setSuccess(true);
        restResult.setMsg("发送成功");
        restResult.setCode(200);
        return restResult;
    }



    @ResponseBody
    @PostMapping("/sendMQ")
    public RestResult sendMQ(@RequestBody String data){
        mqttGateway.sendToMqtt(data, "topic");

        RestResult restResult = new RestResult();
        restResult.setCode(200);
        restResult.setMsg("发送成功");
        restResult.setSuccess(true);
        return restResult;
    }
}