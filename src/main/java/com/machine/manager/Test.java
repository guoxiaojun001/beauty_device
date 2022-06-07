package com.machine.manager;

import cn.hutool.json.JSONObject;
import sun.misc.BASE64Encoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @ClassName Test
 * @Author 养仓鼠的程序员
 * @Date 2019/12/13 0013 上午 9:12
 * @Version 1.0
 **/
public class Test {
    //用户名
    private static String username = "admin";
    //登录密码
    private static String password = "public";
    //服务器地址
    private static String serverPath = "http://39.98.108.64:18083";
    //当前页
    private static int pageIndex = 1;
    //页大小
    private static int pageSize = 100;

    public static void main(String[] args) throws Exception {
        //账号密码Base64加密
        String authorization = getBase64(username, password);
   
        //查询
        String json = query (serverPath,authorization,pageIndex, pageSize);

//        System.out.println ("===========>" + json);

        JSONObject jsonObject = new JSONObject(json);

        //更新设备状态
        String  clientid = jsonObject.getJSONArray("data").getJSONObject(0).getStr("clientid");

        System.out.println ("=========clientid==>" + clientid);

    }

    private static String query(String serverPath, String authorization, int pageIndex, int pageSize) throws Exception {
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

    private static String getBase64(String admin, String aPublic) throws UnsupportedEncodingException {
        final String text = admin+":"+aPublic;
        final BASE64Encoder encoder = new BASE64Encoder();
        final byte[] textByte = text.getBytes("UTF-8");
        return  encoder.encode(textByte);
    }

    class QueryResult {
        private int code;
        private List<ClientInfo> data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public List<ClientInfo> getData() {
            return data;
        }

        public void setData(List<ClientInfo> data) {
            this.data = data;
        }
    }

    class ClientInfo{
        private boolean clean_start;
        private String client_id;
        private String conn_mod;
        private String connected_at;
        private String ipaddress;
        private boolean is_bridge;
        private int keepalive;
        private String node;
        private String peercert;
        private int port;
        private String proto_name;

        public boolean isClean_start() {
            return clean_start;
        }

        public void setClean_start(boolean clean_start) {
            this.clean_start = clean_start;
        }

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = client_id;
        }

        public String getConn_mod() {
            return conn_mod;
        }

        public void setConn_mod(String conn_mod) {
            this.conn_mod = conn_mod;
        }

        public String getConnected_at() {
            return connected_at;
        }

        public void setConnected_at(String connected_at) {
            this.connected_at = connected_at;
        }

        public String getIpaddress() {
            return ipaddress;
        }

        public void setIpaddress(String ipaddress) {
            this.ipaddress = ipaddress;
        }

        public boolean isIs_bridge() {
            return is_bridge;
        }

        public void setIs_bridge(boolean is_bridge) {
            this.is_bridge = is_bridge;
        }

        public int getKeepalive() {
            return keepalive;
        }

        public void setKeepalive(int keepalive) {
            this.keepalive = keepalive;
        }

        public String getNode() {
            return node;
        }

        public void setNode(String node) {
            this.node = node;
        }

        public String getPeercert() {
            return peercert;
        }

        public void setPeercert(String peercert) {
            this.peercert = peercert;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getProto_name() {
            return proto_name;
        }

        public void setProto_name(String proto_name) {
            this.proto_name = proto_name;
        }

        @Override
        public String toString() {
            return "ClientInfo{" +
                    "clean_start=" + clean_start +
                    ", client_id='" + client_id + '\'' +
                    ", conn_mod='" + conn_mod + '\'' +
                    ", connected_at='" + connected_at + '\'' +
                    ", ipaddress='" + ipaddress + '\'' +
                    ", is_bridge=" + is_bridge +
                    ", keepalive=" + keepalive +
                    ", node='" + node + '\'' +
                    ", peercert='" + peercert + '\'' +
                    ", port=" + port +
                    ", proto_name='" + proto_name + '\'' +
                    '}';
        }
    }
}
