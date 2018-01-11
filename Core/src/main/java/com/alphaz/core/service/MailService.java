package com.alphaz.core.service;

import com.alphaz.core.pojo.viewmodel.ResponseModel;

/**
 * @Author: Authur
 * @Date: 2018/1/8 13:19
 */
public interface MailService {

    ResponseModel sendmail(String email,String subject);

    ResponseModel sendHtmlMail(String email,String subject,String content);

    ResponseModel sendAttachmentsMail(String email,String subject,String content, String filePath);
}
