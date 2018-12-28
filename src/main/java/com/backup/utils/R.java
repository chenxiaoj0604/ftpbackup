package com.backup.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Qiang
 * @date 2018/5/7 14:37
 * @description 返回结果
 */
@Slf4j
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R(){
        put("code",0);
        put("msg","操作成功");
    }

    public R(String code, String msg){
        put("code",code);
        put("msg",msg);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static R ok() {
        return ok(0, "操作成功");
    }

    public static R ok(String msg) {
        return ok(0, msg);
    }

    public static R ok(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R map(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }


    public static R error() {
        return error(1, "操作失败");
    }

    public static R error(String msg) {
        return error(1, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public boolean isSuccess(){
        return Objects.equals(String.valueOf(get("code")),"0");
    }

    public String getMsg(){
        return (String) get("msg");
    }



}
