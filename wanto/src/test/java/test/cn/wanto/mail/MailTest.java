/*
 * test.cn.wanto.mail.MailTest.java
 * Aug 24, 2012 
 */
package test.cn.wanto.mail;

import java.io.File;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import cn.touchin.Contexts;

/**
 * Aug 24, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
public class MailTest {

    // private static MailSender mailSender;
    // private static JavaMailSender mailSender;

    private static MailSender mailSender;
    private static SimpleMailMessage templateMessage;

    @BeforeClass
    public static void beforeClass() {
        Contexts.init(new ClassPathXmlApplicationContext("spring/context.xml"));
        // mailSender = Contexts.getBean(JavaMailSender.class);
        mailSender = Contexts.getBean(MailSender.class);
        templateMessage = Contexts.getBean(SimpleMailMessage.class);
    }

    @Test
    public void send() {
        // try {
        // MimeMessage msg = mailSender.createMimeMessage();
        // // SimpleMailMessage msg = new
        // // SimpleMailMessage(mailSender.createMimeMessage());
        // MimeMessageHelper msgHelper = new
        // MimeMessageHelper(msg,false,"utf-8");
        //
        // msgHelper.setFrom("www_happy112_com@163.com");
        // msgHelper.setTo("88052350@qq.com");
        // msgHelper.setSubject("测试情况");
        // msgHelper.setText("不中的情况", true);
        // mailSender.send(msg);
        // } catch (MessagingException e) {
        // throw new RuntimeException("发送邮件失败！", e);
        // }
    }
    
    @Test
    public void send1() {
        // Do the business calculations...
        // Call the collaborators to persist the order...
        // Create a thread safe "copy" of the template message and customize it
//        new FreemarkerKit().genInputStream(template, data);
        
        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
        msg.setTo("88052350@qq.com");
        msg.setText("测试对不对，中文默认会是乱码么");
        try {
            this.mailSender.send(msg);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
            throw new RuntimeException(ex);
           
        }
    }

    public void sendPre() {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("88052350@qq.com"));
                mimeMessage.setFrom(new InternetAddress("www_happy112_com@163.com"));
                mimeMessage.setText("Dear XXX, thank you for placing order.");
            }
        };
        try {
//            this.mailSender.send(preparator);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }

    public void sendmine() throws MessagingException {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("mail.host.com");
        MimeMessage message = sender.createMimeMessage();
        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("test@host.com");
        helper.setText("Check out this image!");
        // let's attach the infamous windows Sample file (this time copied to
        // c:/)
        FileSystemResource file = new FileSystemResource(new File("c:/Sample.jpg"));
        helper.addAttachment("CoolImage.jpg", file);
        sender.send(message);
    }

    public void snedHtml() throws MessagingException {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("mail.host.com");
        MimeMessage message = sender.createMimeMessage();
        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("test@host.com");
        // use the true flag to indicate the text included is HTML
        helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);
        // let's include the infamous windows Sample file (this time copied to
        // c:/)
        FileSystemResource res = new FileSystemResource(new File("c:/Sample.jpg"));
        helper.addInline("identifier1234", res);
        sender.send(message);
    }
}
