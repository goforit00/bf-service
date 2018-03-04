package com.goforit.firstapple.common.integration.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Properties;

/**
 * Created by goforit on 17/4/13.
 */
public class MailInfo {

    /**
     * 发送邮件的服务器的IP
     */
    private String mailServerHost;

    /**
     * 发送邮件的服务器的端口
     */
    private String mailServerPort = "25";

    /**
     * 邮件发送者的地址
     */
    private String   fromAddress;

    /**
     * 邮件接收者的地址
     */
    private String[] toAddress;

    /**
     * 登陆邮件发送服务器的用户名
     */
    private String   userName;

    /**
     * 登陆邮件发送服务器的密码
     */
    private String   password;

    /**
     * 是否需要身份验证
     */
    private boolean validate = false;

    /**
     * 邮件主题
     */
    private String   subject;

    /**
     * 邮件的文本内容
     */
    private String   content;

    /**
     * 邮件附件的文件名
     */
    private String[] attachFileNames;

    /**
     * 邮件类型
     * BodyPart#setContent(String, String) 的第二个参数的值
     * "text/plain;charset=utf-8"
     * "text/html;charset=utf-8"
     */
    private String contentType;


    /**
     * 获得邮件会话属性
     */
    public Properties getProperties() {
        Properties pro = new Properties();
        pro.put("mail.smtp.host", this.mailServerHost);
        pro.put("mail.smtp.port", this.mailServerPort);
        pro.put("mail.smtp.auth", validate ? "true" : "false");
        return pro;
    }


    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String[] getToAddress() {
        return toAddress;
    }

    public void setToAddress(String[] toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
