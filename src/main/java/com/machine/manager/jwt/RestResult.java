package com.machine.manager.jwt;

import java.util.HashMap;
import java.util.Map;

/**
 * api 接口返回结果封装
 * @author gxj
 */
public class RestResult {
    private String msg;
    private int code;
    private Object data;

    private boolean success;


    public RestResult(){

    }

    public RestResult(String msg, int code, Object data,boolean success) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.success = success;
    }

    public static RestResult newInstance(){
        return new RestResult();
    }

    public void put(String key,Object data){
        if(this.data==null) {
            this.data = new HashMap<String,Object>();
        }
        ((Map)this.data).put(key,data);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                ", success=" + success +
                '}';
    }
}
