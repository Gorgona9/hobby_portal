package com.hobby.portal.service.impl;

import com.hobby.portal.model.entities.UserEntity;
import com.hobby.portal.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public NotificationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendNotification(UserEntity userEntity) {
        SimpleMailMessage mail = new SimpleMailMessage();
        String mailBody = "http://localhost:4200/password/" + userEntity.getId();
        mail.setTo(userEntity.getEmail());
        mail.setFrom("findyourhobbie@gmail.com");
        mail.setSubject("Change your password");
        mail.setText("Click the link to reset your password: " + mailBody);

        javaMailSender.send(mail);
    }
}
