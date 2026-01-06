package jmpolak.task_notification.core.port;

public interface IMailSenderPort {
    public void sendMail(String to, String subject, String contentText);
}
