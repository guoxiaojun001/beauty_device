package com.machine.manager.config;

import cn.hutool.json.JSONObject;
import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.MachineInfoEntity;
import com.machine.manager.entity.UserInfo;
import com.machine.manager.mqtt.MqttGateway;
import com.machine.manager.service.MachineService;
import com.machine.manager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.util.List;

import static com.machine.manager.entity.TopicType.TList;

/**
 * @author guoxi_789@126.com
 * @date 2020/12/21
 */
@Configuration
@IntegrationComponentScan
@Slf4j
public class MqttReceiveConfig {

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;

    @Value("${spring.mqtt.completionTimeout}")
    private int completionTimeout;   //连接超时


    @Autowired
    private MachineService service;

    @Autowired
    UserService userService;

//    @Autowired
//    MqttGateway mqttGateway;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        mqttConnectOptions.setKeepAliveInterval(2);
        mqttConnectOptions.setMaxInflight(1000);
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    //接收通道
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    //配置client,监听的topic
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId + "_inbound", mqttClientFactory(),
                        TList);
        adapter.setCompletionTimeout(completionTimeout);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    //通过通道获取数据
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String payload = (String) message.getPayload();
                log.info("主题：{}，消息接收到的数据：{}", message.getHeaders().get("mqtt_receivedTopic"), payload);

                JSONObject jsonObject = new JSONObject(payload);
                String messsageType = jsonObject.getStr("messsageType","");
                String machineParam = jsonObject.getStr("machineParam");
                switch (messsageType){
                    case "vue_lock":
                        MachineInfo machineInfo =  service.selectDeviceId(machineParam);
                        if(null != machineInfo){
                            machineInfo.setLockStatus(jsonObject.getInt("lockStatus"));
                            int xa = service.updateByPrimaryKeySelective(machineInfo);
                            log.info(xa +"===============更新锁定状态刷新==>" + machineInfo );
                        }

                        Integer userId = jsonObject.getInt("userId");
                        JSONObject js = new JSONObject();
                        List<MachineInfoEntity> machineInfoList;
                        UserInfo userInfo = userService.selectByPrimaryKey(userId);
                        if(userInfo.getUserType().equals("admin")){
                            //需要区分出 用户类型，防止查找的数据太多
                            machineInfoList = service.selectAllByNormalWithParm22(null,null,0,1000);
                            js.put("data",machineInfoList);
                            js.putByPath("messsageType","web_device_status");

//                            mqttGateway.sendToMqtt("{'messsageType':'update'}","/device_list/device_status");

                            log.info("===============通知管理员后台刷新==>" + js.toString());
                        }else {
                            machineInfoList = service.selectAllByNormalWithParm22(userId,null,0,1000);
                            js.put("data",machineInfoList);
                            js.putByPath("messsageType","web_device_status");
//                            mqttGateway.sendToMqtt(js.toString(),"/"  + userId + "/device_status");

//                            mqttGateway.sendToMqtt("{'messsageType':'update'}","/device_list/device_status");

                            log.info("===============通知经销商后台刷新==>" + js.toString());
                        }

                        break;

                    case "vue_notice":
                        //直接手机端接收，后端不处理
                        break;
                }

            }
        };
    }
}

