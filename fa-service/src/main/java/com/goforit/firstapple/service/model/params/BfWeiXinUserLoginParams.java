package com.goforit.firstapple.service.model.params;

/**
 * Created by goforit on 18/2/4.
 */
public class BfWeiXinUserLoginParams {

    private String code;

    private String nickName;

    private String avatarUrl;

    public BfWeiXinUserLoginParams(){}

    public BfWeiXinUserLoginParams(String code,String nickName,String avatarUrl){
        this.code=code;
        this.nickName=nickName;
        this.avatarUrl=avatarUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
