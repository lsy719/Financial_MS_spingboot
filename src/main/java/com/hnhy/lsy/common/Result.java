package com.hnhy.lsy.common;

public class Result<T> {
//    响应码
    private String code;
//    响应消息
    private String msg;
//    响应数据
    private T data;//T就是泛型，可以包含所有数据类型

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(T data){
        this.data = data;
    }


    public static com.hnhy.lsy.common.Result success() {
        com.hnhy.lsy.common.Result result = new com.hnhy.lsy.common.Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> com.hnhy.lsy.common.Result<T> success(T data) {
        com.hnhy.lsy.common.Result<T> result = new com.hnhy.lsy.common.Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static com.hnhy.lsy.common.Result error(String code, String msg) {
        com.hnhy.lsy.common.Result result = new com.hnhy.lsy.common.Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
