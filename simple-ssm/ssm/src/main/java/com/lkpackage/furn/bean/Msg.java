package com.lkpackage.furn.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author UnknownCode
 * @version 1.0
 * @date 2023/6/8 19:35
 */
// 返回给浏览器的对象
public class Msg {
    private String msg; // 返回信息 比如 success 或者 error
    private String code;  // 返回状态码 比如 400
    private Map<String,Object> datapools = new HashMap<String,Object>();

    public static Msg success(){
        Msg msg = new Msg();
        msg.setCode("200");
        msg.setMsg("success");
        return msg;
    }
    public static Msg error(){
        Msg msg = new Msg();
        msg.setCode("400");
        msg.setMsg("error");
        return msg;
    }
    public Msg add (String key,Object object){
        datapools.put(key,object);
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getDatapools() {
        return datapools;
    }

    public void setDatapools(Map<String, Object> datapools) {
        this.datapools = datapools;
    }
}
