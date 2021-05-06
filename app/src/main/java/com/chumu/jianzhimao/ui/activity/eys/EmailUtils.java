package com.chumu.jianzhimao.ui.activity.eys;

import android.util.Log;


import androidx.cardview.widget.CardView;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *     //发送mail
 *     implementation 'com.sun.mail:android-mail:1.6.2'
 *     implementation 'com.sun.mail:android-activation:1.6.2'
 * EmailUtils.get163()
 *                 .setEmailName("管理员发送短信业务邮件")
 *                 .setReceiveEmailAccounts("15035179243@163.com","15035179243")
 *                 .sendMail("您有新的验证码，请注意查收","感谢您对我们工作的支持，您的验证码是：303054");
 * @author 邮件发送工具类
 */
public class EmailUtils {
    /*
     * 邮箱信息
     */
    private static String EmailAccount;
    private static String EmailName;
    private static String EmailPassword;
    private static String SMTPHost;
    private static String SMTPPort;
    private static String SPort;
    private static EmailUtils emailUtils;
    private static String UserName;
    private static HashMap<String, String> mReceiveEmailAccounts = new HashMap<>();
    public static HashMap<String, String> CCEmailAccount = new HashMap<>();
    public static EmailUtils get163() {
        if (emailUtils == null) {
            emailUtils = new EmailUtils();
        }
        EmailAccount = "chumuemail@163.com";
        EmailName = "chumuya";
        EmailPassword = "LTEWMOYGIBSZSSJC";
        SMTPHost = "smtp.163.com";
        SMTPPort = "25";
        SPort = "465";
        UserName= "BLE_STATION";
        return emailUtils;
    }

    public EmailUtils setEmailAccount(String emailAccount) {
        EmailAccount = emailAccount;
        return emailUtils;
    }

    public EmailUtils setReceiveEmailAccounts(HashMap<String ,String> receiveEmailAccounts) {
        mReceiveEmailAccounts = receiveEmailAccounts;
        return emailUtils;
    }

    public EmailUtils setReceiveEmailAccounts(String email,String name) {
        mReceiveEmailAccounts.put(email,name);
        return emailUtils;
    }

    public EmailUtils setEmailName(String emailName) {
        EmailName = emailName;
        return emailUtils;
    }

    public EmailUtils setUserName(String userName) {
        UserName = userName;
        return emailUtils;
    }

    public EmailUtils setEmailPassword(String emailPassword) {
        EmailPassword = emailPassword;
        return emailUtils;
    }

    public EmailUtils setSMTPHost(String mSMTPHost) {
        SMTPHost = mSMTPHost;
        return emailUtils;
    }

    public EmailUtils setSMTPPort(String mSMTPPort) {
       SMTPPort = mSMTPPort;
        return emailUtils;
    }

    public EmailUtils setSPort(String mSPort) {
       SPort = mSPort;
        return emailUtils;
    }

    public  void sendMail(final String subject, final String content,onCallBack onCallBack) {

        try {

             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     Properties props = new Properties();
                     props.setProperty("mail.transport.protocol", "smtp");
                     props.setProperty("mail.smtp.host", SMTPHost);
                     props.setProperty("mail.smtp.port", SMTPPort);
                     props.setProperty("mail.smtp.auth", "true");
                     props.setProperty("debug", "true");
                     props.setProperty("mail.smtp.starttls.enable", "true");
            /*
            props.put("mail.smtp.socketFactory.port", SPort);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            */
                     try {

                         props.setProperty("mail.smtp.timeout", "10000");
                         props.setProperty("mail.smtp.connectiontimeout", "10000");

                         Session session = Session.getDefaultInstance(props, new SimpleAuthenticator(UserName, EmailPassword));
                         session.setDebug(false);

                         MimeMessage message = new MimeMessage(session);

                         message.setFrom(new InternetAddress(EmailAccount, EmailName, "UTF-8"));
                         for (String toEmail : mReceiveEmailAccounts.keySet()) {
                             message.addRecipient(MimeMessage.RecipientType.TO,
                                     new InternetAddress(toEmail, mReceiveEmailAccounts.get(toEmail), "UTF-8"));
                         }
//                         for (String toEmail : CCEmailAccount.keySet()) {
//                             message.addRecipient(MimeMessage.RecipientType.CC,
//                                     new InternetAddress(toEmail, CCEmailAccount.get(toEmail), "UTF-8"));
//                         }
                         message.setSubject(subject, "UTF-8");
                         message.setContent(content, "text/html;charset=UTF-8");
                         message.setSentDate(new Date());
                         message.saveChanges();

                         Transport transport = session.getTransport();
                         transport.connect(EmailAccount, EmailPassword);
                         transport.sendMessage(message, message.getAllRecipients());
                         Log.e(getClass().getSimpleName(),  "邮件【" + subject + "】发送成功" );

                         transport.close();
                         onCallBack.CallS();
                     } catch (Exception e) {
                         //            RestartAPPTool.restartAPP(ShowRoomAPP.getInstance());
                         Log.e(getClass().getSimpleName(),  e.toString());
                         onCallBack.CallF();
                     }
                 }
             }).start();



        } catch (Exception e) {
            e.printStackTrace();
            onCallBack.CallF();
        }



//            RestartAPPTool.restartAPP(ShowRoomAPP.getInstance());


    }
    public  interface  onCallBack{
        void  CallS();
        void  CallF();
    }
    static class SimpleAuthenticator extends Authenticator {
        private String user;
        private String pwd;

        public SimpleAuthenticator(String user, String pwd) {
            this.user = user;
            this.pwd = pwd;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pwd);
        }
    }
}
