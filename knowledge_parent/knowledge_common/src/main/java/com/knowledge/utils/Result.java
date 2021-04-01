package com.knowledge.utils;

import com.alibaba.fastjson.annotation.JSONType;
import com.knowledge.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应数据
 */
@JSONType(orders={"code","msg","data"})
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编码：200表示成功，其他值表示失败
     */
    private int code = 200;
    /**
     * 消息内容
     */
    private String msg = "success";
    /**
     * 响应数据
     */
    private T data;

    public Result(){
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public Result<T> ok(T data,String reportId) {
        this.setData(data);
        return this;
    }

    public Result<T> success(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean success(){
        return code == 0 ? true : false;
    }

    public Result<T> error() {
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = MessageUtils.getMessage(this.code);
        return this;
    }

    public Result<T> error(int code) {
        this.code = code;
        this.msg = MessageUtils.getMessage(this.code);
        return this;
    }

    public Result<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public Result<T> error(String msg) {
        this.code = ErrorCode.INTERNAL_SERVER_ERROR;
        this.msg = msg;
        return this;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
