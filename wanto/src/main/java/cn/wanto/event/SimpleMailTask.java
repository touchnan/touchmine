/*
 * cn.wanto.event.SimpleMailTask.java
 * Oct 15, 2012 
 */
package cn.wanto.event;

import org.nutz.log.Logs;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Oct 15, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class SimpleMailTask implements Runnable {

    private MailSender mailSender;
    private SimpleMailMessage msg;

    public SimpleMailTask(MailSender mailSender, SimpleMailMessage simpleMailMessage) {
        this.mailSender = mailSender;
        this.msg = simpleMailMessage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            mailSender.send(msg);
        } catch (MailException e) {
            Logs.getLog(SimpleMailTask.class).error(e);
        }
    }

}
