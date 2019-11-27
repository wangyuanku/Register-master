package com.lcz.register.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class JavaMailUtil {

    public static String from = "504621552@qq.com";
    public static String emailPassword = "wykAndrew..";
    public static String emailSMTPHost = "smtp.qq.com";
    public static String receiveMailAccount;

    public static MimeMessage createMimeMessage(Session session, String from,String receiveMail,String html)throws MessagingException, IOException{
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 2.1设置发件人
        message.setFrom(new InternetAddress(from));
        // 2.2设置接收人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail));;
        // 4、Subject:邮件主题
        message.setSubject("邮箱验证","UTF-8");
        // 5、Content:邮件正文（可以使用Html标签）
        message.setContent(html,"text/html;charset=UTF-8");
        // 6、设置发送时间
        message.setSentDate(new Date());
        // 7、保存设置
        message.saveChanges();
        // 8、将该邮件保存在本地
        //OutputStream out = new FileOutputStream("D://MyEmail" + UUID.randomUUID().toString() + ".eml");
        //message.writeTo(out);
        //out.flush();
        //out.close();
        return message;
    }
}
