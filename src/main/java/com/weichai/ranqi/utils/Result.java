package com.weichai.ranqi.utils;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();


    // 构造方法
    public Result(Boolean success, Integer code, String message, Map<String, Object> data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 获取 success
    public Boolean getSuccess() {
        return success;
    }

    // 设置 success
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    // 获取 code
    public Integer getCode() {
        return code;
    }

    // 设置 code
    public void setCode(Integer code) {
        this.code = code;
    }

    // 获取 message
    public String getMessage() {
        return message;
    }

    // 设置 message
    public void setMessage(String message) {
        this.message = message;
    }

    // 获取 data
    public Map<String, Object> getData() {
        return data;
    }

    // 设置 data
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    // 向 data 中添加单个键值对
    public void addData(String key, Object value) {
        this.data.put(key, value);
    }

    // 快速创建成功响应
    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }



    // 快速创建失败响应
    public static Result error(String message) {
        return new Result(false, 500, message, null);
    }

    // 默认构造方法
    private Result() {
    }


    // 快速创建响应，带自定义状态码
    public static Result build(Boolean success, Integer code, String message, Map<String, Object> data) {
        return new Result(success, code, message, data);
    }

    public static Result ok(){
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static Result error(){
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    // 为 data 添加多个键值对
    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
    // 为 data 添加多个键值对
    public Result data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
