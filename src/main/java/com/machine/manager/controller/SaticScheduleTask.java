package com.machine.manager.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.machine.manager.entity.MachineInfo;
import com.machine.manager.entity.MachineInfoEntity;
import com.machine.manager.mqtt.MqttGateway;
import com.machine.manager.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    //用户名
    private static String username = "admin";
    //登录密码
    private static String password = "qunimabi#123$456";
//    private static String password = "public";
    //服务器地址
//    private static String serverPath = "http://localhost:18083";
    private static String serverPath = "http://172.18.130.64:18083";
    //当前页
    private static int pageIndex = 1;
    //页大小
    private static int pageSize = 100;


//    @Autowired
//    MqttGateway mqttGateway;

    @Autowired
    private MachineService service;


    //3.添加定时任务
//    @Scheduled(cron = "0/30 * * * * ?")
    //或直接指定时间间隔，例如：30秒
    @Scheduled(fixedRate=600000)
    public void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        //账号密码Base64加密
        String authorization = null;
        try {
            authorization = getBase64(username, password);

            //查询
            String json = query (serverPath,authorization,pageIndex, pageSize);
            System.out.println ("===========>" + json);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
//            String  clientid = jsonObject.getJSONArray("data").getJSONObject(0).getStr("clientid");
            if(!StringUtils.isEmpty(json)){
                if(null == service) return;
                List<MachineInfoEntity> machineInfoList = service.selectAllByNormalWithParm22(null,null,0,1000);
                for(int i = 0 ; i < jsonArray.size(); i ++){
                    JSONObject o =  jsonArray.getJSONObject(i);

                    Iterator<MachineInfoEntity> iterator = machineInfoList.iterator();
                    while (iterator.hasNext()) {
                        MachineInfoEntity machineInfo = iterator.next();
                        if (machineInfo.getMachineParam().equals(o.getStr("clientid"))) {
                            machineInfo.setOnlineStatus(o.getBool("connected",false) ? 1 :0);
                            machineInfo.setLastloginTime(o.getStr("connected_at",""));
                            System.out.println("更新在线状态和登录时间 : " +machineInfo);

                            updateMachineInfo(machineInfo);
                            iterator.remove();
                        }
                    }

                    System.out.println("将剩余部分设备设置为离线 ： " + machineInfoList);
                    for(MachineInfoEntity machineInfoEntity : machineInfoList){
                        machineInfoEntity.setOnlineStatus( 0);
                        updateMachineInfo(machineInfoEntity);

                    }
                }
            }

            System.err.println("执行静态定时任务时间2: " + LocalDateTime.now());
            //通知前端更新
//            mqttGateway.sendToMqtt("{'messsageType':'update'}","/device_list/device_status");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void updateMachineInfo(MachineInfoEntity machineInfo){
        MachineInfo mf = new MachineInfo();
        mf.setId(machineInfo.getId());
        mf.setCooperationMode(machineInfo.getCooperationMode());
        mf.setLastloginTime(machineInfo.getLastloginTime());
        mf.setCreateTime(machineInfo.getCreateTime());
        mf.setDeviceSn(machineInfo.getDeviceSn());
        mf.setLockStatus(machineInfo.getLockStatus());

        mf.setOnlineStatus(machineInfo.getOnlineStatus());
        mf.setMachineAttribute(machineInfo.getMachineAttribute());
        mf.setMachineBrand(machineInfo.getMachineBrand());
        mf.setLeftTime(machineInfo.getLeftTime());
        mf.setMachineCity(machineInfo.getMachineCity());
        mf.setMachineCityId(machineInfo.getMachineCityId());

        mf.setMachineParam(machineInfo.getMachineParam());
        mf.setMachineFunction(machineInfo.getMachineFunction());
        mf.setMachineProvice(machineInfo.getMachineProvice());
        mf.setMachineProviceId(machineInfo.getMachineProviceId());
        mf.setMachineStatus(machineInfo.getMachineStatus());
        mf.setMachineType(machineInfo.getMachineType());

        mf.setUserId(machineInfo.getUserId());
        mf.setOtherParm(machineInfo.getOtherParm());
        mf.setStoreId(machineInfo.getStoreId());
        mf.setMachineWorkTimeOnce(machineInfo.getMachineWorkTimeOnce());
        mf.setUsedDuration(machineInfo.getUsedDuration());
        mf.setUserName(machineInfo.getOwner());

        service.updateByPrimaryKeySelective(mf);
    }

    public static String query(String serverPath, String authorization, int pageIndex, int pageSize) throws Exception {
        //拼接查询参数
        String param = "_page=" + pageIndex + "&" + "_limit=" + pageSize;
        String queryPath = "/api/v4/clients?"+param;

        URL url = new URL(serverPath+queryPath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //连接认证信息放在头里,注意,base64可以反编码,有安全隐患
        conn.setRequestProperty("authorization", "Basic "+authorization);
        conn.setRequestMethod("GET");
        // 开始连接
        conn.connect();

        String resule = null ;
        if (conn.getResponseCode() == 200) {
            // 请求返回的数据
            InputStream in = conn.getInputStream();
            try {
                byte[] data1 = new byte[in.available()];
                in.read(data1);
                // 转成字符串
                resule = new String(data1);
            } catch (Exception e) {
                e.printStackTrace ();
            }
        } else {
            throw new Exception ("获取客户端信息失败");
        }

        return resule;
    }

    public static String getBase64(String admin, String aPublic) throws UnsupportedEncodingException {
        final String text = admin+":"+aPublic;
        final BASE64Encoder encoder = new BASE64Encoder();
        final byte[] textByte = text.getBytes("UTF-8");
        return  encoder.encode(textByte);
    }
}