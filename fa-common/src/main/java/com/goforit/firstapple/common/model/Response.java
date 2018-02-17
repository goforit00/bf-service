package com.goforit.firstapple.common.model;

import java.io.Serializable;

/**
 * Created by goforit on 17/4/1.
 */
public class Response<T> implements Serializable {
    
    private boolean success;
    
    private String message;
    
    private T data;
    
    public Response(){}
    
    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public static Response buildSuccess(Object data){
        Response result= new Response();
        result.setSuccess(true);
        result.setData(data);

        return result;
    }

    public static Response buildFailed(String message){
        Response result= new Response();

        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    //~~~~~ getter and setter ~~~~~


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
