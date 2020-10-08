package com.presto.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailApp {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("prestoprojeto@gmail.com", "presto@projeto1");
            }
        });

        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prestoprojeto@gmail.com"));

            Address[] toUser = InternetAddress.parse("fastcaf@hotmail.com, carlosaugustofast@gmail.com");

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Teste de email JavaMail");
            message.setText("Teste de email com o JavaMail texto");
            Transport.send(message);

            System.out.println("Feito!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}