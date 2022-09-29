package com.example.thread_no_known.resp;

import java.io.Serializable;
import java.util.Map;

public class ResponseResult implements Serializable {

    public final static int success = 200;

    public final static int faild = 0;

    public final static String success_msg = "success";
    public final static String fail_msg = "fail";
    private static final long serialVersionUID = 2563315000570793330L;


    /**
     * 消息码。
     * 默认：code = 0
     * 成功：code > 0
     * 失败：code < 1
     */
    public int code = 0;
    /**
     * 提示信息
     */
    public String msg = "亲，系统繁忙！";

    public Map<String, Object> data;

    public ResponseResult(){

    }

    public ResponseResult(int code, String msg, Map<String,Object> data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
