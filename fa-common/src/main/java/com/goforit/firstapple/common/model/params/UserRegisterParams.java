package com.goforit.firstapple.common.model.params;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by goforit on 17/4/5.
 */
public class UserRegisterParams {

    private String userNickName;

    private String password;

    private String email;

    public UserRegisterParams(String userNickName,String password,String email){
        this.userNickName=userNickName;
        this.password=password;
        this.email=email;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
