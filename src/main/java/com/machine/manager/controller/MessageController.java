package com.machine.manager.controller;


import com.machine.manager.mqtt.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * mqtt controller
 *
 * @author guoxi_789@126.com
 * @date 2020/12/15
 */
@RestController
public class MessageController {
    @Autowired
    MqttGateway mqttGateway;

    /***
     * 发布消息，用于其他客户端消息接收测试
     */

    @RequestMapping(value = "/sendMqttMessage",method = RequestMethod.GET)
    public String sendMqttMessage(String message, String topic) {
        mqttGateway.sendToMqtt(message, topic);
        return "ok";
    }
}