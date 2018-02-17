package com.goforit.firstapple.common.integration.client;

import com.goforit.firstapple.common.integration.model.MailInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by goforit on 17/4/13.
 */
public interface MailSenderClient {

    /**
     * 发送邮件
     * @param mailInfo
     * @return
     */
    boolean sendMail(MailInfo mailInfo);


    /**
     * 发送邮件
     * @param subject
     * @param receiveAddressList
     * @param freeMarkerTemplatePath
     * @param mailParams
     * @return
     */
    boolean sendEmail(String subject, List<String> receiveAddressList, String freeMarkerTemplatePath,
                      Map<String, Object> mailParams);
}
