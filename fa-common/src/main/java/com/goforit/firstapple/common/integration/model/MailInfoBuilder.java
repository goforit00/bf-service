package com.goforit.firstapple.common.integration.model;

/**
 * Created by goforit on 17/4/13.
 */
public class MailInfoBuilder {

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
    private boolean  validate = false;

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
     */
    private String   contentType;



    private MailInfoBuilder() {
    }

    public static MailInfoBuilder aMailInfo() {
        return new MailInfoBuilder();
    }

    public MailInfoBuilder withMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
        return this;
    }

    public MailInfoBuilder withMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
        return this;
    }

    public MailInfoBuilder withFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
        return this;
    }

    public MailInfoBuilder withToAddress(String[] toAddress) {
        this.toAddress = toAddress;
        return this;
    }

    public MailInfoBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public MailInfoBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public MailInfoBuilder withValidate(boolean validate) {
        this.validate = validate;
        return this;
    }

    public MailInfoBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailInfoBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public MailInfoBuilder withAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
        return this;
    }

    public MailInfoBuilder withContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public MailInfoBuilder but() {
        return aMailInfo()
                .withMailServerHost(mailServerHost)
                .withMailServerPort(mailServerPort)
                .withFromAddress(fromAddress)
                .withToAddress(toAddress)
                .withUserName(userName)
                .withPassword(password)
                .withValidate(validate)
                .withSubject(subject)
                .withContent(content)
                .withAttachFileNames(attachFileNames)
                .withContentType(contentType);
    }

    public MailInfo build() {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setMailServerHost(mailServerHost);
        mailInfo.setMailServerPort(mailServerPort);
        mailInfo.setFromAddress(fromAddress);
        mailInfo.setToAddress(toAddress);
        mailInfo.setUserName(userName);
        mailInfo.setPassword(password);
        mailInfo.setValidate(validate);
        mailInfo.setSubject(subject);
        mailInfo.setContent(content);
        mailInfo.setAttachFileNames(attachFileNames);
        mailInfo.setContentType(contentType);
        return mailInfo;
    }
}
