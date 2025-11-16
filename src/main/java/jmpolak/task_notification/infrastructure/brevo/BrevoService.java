package jmpolak.task_notification.infrastructure.brevo;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import brevoApi.TransactionalEmailsApi;
import brevoModel.SendSmtpEmail;
import brevoModel.SendSmtpEmailSender;
import brevoModel.SendSmtpEmailTo;
import jmpolak.task_notification.core.port.IMailSenderPort;

@Service
public class BrevoService implements IMailSenderPort {

    private final TransactionalEmailsApi api;
    @Value("${brevo.mail.sender}")
    private String mailSender;

    BrevoService(TransactionalEmailsApi api) {
        this.api = api;
    }

    @Override
    public void sendMail(String to, String subject, String htmlBody) {
        SendSmtpEmail email = new SendSmtpEmail();
        email.setSender(new SendSmtpEmailSender().email(this.mailSender).name("task123"));
        email.setTo(Collections.singletonList(new SendSmtpEmailTo().email(to)));
        email.setSubject(subject);
        email.setHtmlContent(htmlBody);

        try {
            api.sendTransacEmail(email);
        } catch (brevo.ApiException e) {
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response body: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            throw new RuntimeException("Failed to send email", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
