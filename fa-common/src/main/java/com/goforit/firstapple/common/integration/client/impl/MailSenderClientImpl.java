package com.goforit.firstapple.common.integration.client.impl;

import com.goforit.firstapple.common.integration.client.MailSenderClient;
import com.goforit.firstapple.common.integration.model.MailAuth;
import com.goforit.firstapple.common.integration.model.MailInfo;
import com.goforit.firstapple.common.integration.model.MailInfoBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by goforit on 17/4/13.
 */
public class MailSenderClientImpl implements MailSenderClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSenderClientImpl.class);

    /**
     * 默认邮箱服务器 host
     */
    private String defaultServerHost;

    /**
     * 默认邮箱服务器 port
     */
    private String defaultServerPort;

    /**
     *
     */
    private String defaultAccount;

    /**
     * 默认账户密码
     */
    private String defaultPassword;

    /**
     * 默认发送地址
     */
    private String defaultFromAddress;

    @Override
    public boolean sendMail(MailInfo mailInfo) {
        boolean flag = false;

        // 判断是否需要身份验证
        MailAuth authenticator = null;
        Properties props = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 创建一个密码验证器
            authenticator = new MailAuth(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据会话属性和密码验证器构造一个发送邮件的 session
        Session sendSession = Session.getDefaultInstance(props, authenticator);

        try {
            Message sendMailMessage = new MimeMessage(sendSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            sendMailMessage.setFrom(from);

            // 创建邮件接收者地址
            String[] tos = mailInfo.getToAddress();
            if (tos != null && tos.length != 0) {
                InternetAddress[] to = new InternetAddress[tos.length];
                // 设置邮件消息的发送者
                for (int i = 0; i < tos.length; i++) {
                    to[i] = new InternetAddress(tos[i]);
                }
                sendMailMessage.setRecipients(Message.RecipientType.TO, to);
            }

            // 设置邮件主题
            String mSubject = StringUtils.isEmpty(mailInfo.getSubject()) ? "" : mailInfo.getSubject();
            sendMailMessage.setSubject(MimeUtility.encodeText(mSubject, "utf-8", "B"));
            // 设置邮件内容
            // sendMailMessage.setText(mailInfo.getContent());
            Multipart multipart = new MimeMultipart();
            // 邮件文本内容
            if (mailInfo.getContent() != null && !"".equals(mailInfo.getContent())) {
                BodyPart part = new MimeBodyPart();
                part.setContent(mailInfo.getContent(), mailInfo.getContentType());// 设置邮件文本内容
                multipart.addBodyPart(part);
            }

            // 附件
            String attachFileNames[] = mailInfo.getAttachFileNames();
            int leng = attachFileNames == null ? 0 : attachFileNames.length;
            for (int i = 0; i < leng; i++) {
                BodyPart part = new MimeBodyPart();
                // 根据文件名获取数据源
                DataSource dataSource = new FileDataSource(attachFileNames[i]);
                DataHandler dataHandler = new DataHandler(dataSource);
                // 得到附件本身并至入BodyPart
                part.setDataHandler(dataHandler);
                // 得到文件名同样至入BodyPart
                part.setFileName(MimeUtility.encodeText(dataSource.getName()));
                multipart.addBodyPart(part);
            }

            sendMailMessage.setContent(multipart);
            sendMailMessage.setSentDate(new Date());
            // 发送邮件
            Transport transport = sendSession.getTransport("smtp");
            transport.connect(mailInfo.getUserName(), mailInfo.getPassword());
            Transport.send(sendMailMessage, sendMailMessage.getAllRecipients());

            transport.close();

            flag = true;
        } catch (AddressException e) {
            LOGGER.error("邮件地址信息异常:", e);
        } catch (MessagingException e) {
            LOGGER.error("构造邮件内容异常:", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("不支持的邮件编码异常:", e);
        }

        return flag;
    }

    @Override
    public boolean sendEmail(String subject, List<String> receiveAddressList, String freeMarkerTemplatePath,
        Map<String, Object> mailParams) {

        if (CollectionUtils.isEmpty(receiveAddressList)) {
            LOGGER.warn("收件人列表不能为空:邮件主题={}", subject);
            return false;
        }
        try {
            String mailContent = FreemarkerUtil.process(freeMarkerTemplatePath, mailParams);
            MailInfo mailInfo = getDefaultHtmlMailInfoBuilder()
                .withToAddress(receiveAddressList.toArray(new String[receiveAddressList.size()]))
                .withSubject(subject)
                .withContent(mailContent)
                .build();
            return sendMail(mailInfo);
        } catch (Exception e) {
            LOGGER.warn("发送邮件异常", e);
            return false;
        }
    }

    public MailInfoBuilder getDefaultHtmlMailInfoBuilder() {
        MailInfoBuilder builder = getDefaultMailInfoBuilder();
        builder.withContentType("text/html;charset=utf-8");
        return builder;
    }

    public MailInfoBuilder getDefaultMailInfoBuilder() {
        return MailInfoBuilder.aMailInfo()
            .withMailServerHost(defaultServerHost)
            .withMailServerPort(defaultServerPort)
            .withFromAddress(defaultFromAddress)
            .withUserName(defaultAccount)
            .withPassword(defaultPassword)
            .withContentType("text/plain;charset=utf-8")
            .withValidate(true);
    }

    public String getDefaultServerHost() {
        return defaultServerHost;
    }

    public void setDefaultServerHost(String defaultServerHost) {
        this.defaultServerHost = defaultServerHost;
    }

    public String getDefaultServerPort() {
        return defaultServerPort;
    }

    public void setDefaultServerPort(String defaultServerPort) {
        this.defaultServerPort = defaultServerPort;
    }

    public String getDefaultAccount() {
        return defaultAccount;
    }

    public void setDefaultAccount(String defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public String getDefaultFromAddress() {
        return defaultFromAddress;
    }

    public void setDefaultFromAddress(String defaultFromAddress) {
        this.defaultFromAddress = defaultFromAddress;
    }
}
