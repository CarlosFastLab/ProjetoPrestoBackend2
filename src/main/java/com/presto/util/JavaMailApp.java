package com.presto.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailApp {

    private  Session session;
    private Properties props;
    public JavaMailApp (){
        this.props = new Properties();
        this.props.put("mail.smtp.host", "smtp.gmail.com");
        this.props.put("mail.smtp.socketFactory.port", "465");
        this.props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.smtp.port", "465");

         this.session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("prestoprojeto@gmail.com", "presto@projeto1");
            }
        });

        session.setDebug(true);
    }

        public void enviarEmail(String email, String senha) {
            try {

                Message message = new MimeMessage(this.session);
                message.setFrom(new InternetAddress("prestoprojeto@gmail.com"));

                Address[] toUser = InternetAddress.parse(email);

                message.setRecipients(Message.RecipientType.TO, toUser);
                message.setSubject("Redefinição de senha Presto");
                message.setText("Essa é sua nova senha, " + senha);
                Transport.send(message);

                System.out.println("Feito!");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
