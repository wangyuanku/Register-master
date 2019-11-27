package com.lcz.register.web;

import com.lcz.register.utils.HtmlText;
import com.lcz.register.utils.JavaMailUtil;
import com.lcz.register.utils.RandonUtil;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        try{
            //获取发送的邮箱地址
            String email = req.getParameter("email");
            JavaMailUtil.receiveMailAccount = email;

            final String authorizationCode = "aqjzjahzmrksbidg";//获取授权码
            Properties properties = System.getProperties();
            properties.setProperty("smtp.qq.com",JavaMailUtil.emailSMTPHost);//设置邮箱服务器
            properties.setProperty("mail.smtp.auth", "true");//打开认证

            //QQ邮箱需要下面这段代码，163邮箱不需要
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);


            // 1.获取默认session对象（创建连接对象，连接到邮箱服务器）
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("504621552@qq.com", authorizationCode); // 发件人邮箱账号、授权码
                }
            });

            //创建一封邮箱
            String code = RandonUtil.getRandom();
            String html = HtmlText.html(code);
            MimeMessage message = JavaMailUtil.createMimeMessage(session,JavaMailUtil.from,JavaMailUtil.receiveMailAccount,html);

            //根据session获取邮件传输对象
            Transport transport = session.getTransport();
            //连接邮箱服务区
            transport.connect(JavaMailUtil.from, JavaMailUtil.emailPassword);
            //发送邮件
            transport.sendMessage(message,message.getAllRecipients());
            //关闭连接
            transport.close();
            //写入session
            req.getSession().setAttribute("code",code);
        } catch (Exception e){
            e.printStackTrace();
            req.getSession().setAttribute("error","邮件发送失败!");
        }
    }
}
