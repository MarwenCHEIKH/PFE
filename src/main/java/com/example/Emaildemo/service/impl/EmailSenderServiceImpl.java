package com.example.Emaildemo.service.impl;

import com.example.Emaildemo.service.EmailSenderService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender mailSender;

    public EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override

    public void sendEmail(String to, String subject, String message, MultipartFile file) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject(subject);
        helper.setFrom("marouene.cheikh@gmail.com");
        helper.setTo(to);
        helper.setText(message, true);

        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            InputStreamSource source = new InputStreamSource() {

                @Override
                public InputStream getInputStream() throws IOException {
                    return file.getInputStream();
                }
            };
            helper.addAttachment(fileName,source);

        }
        mailSender.send(mimeMessage);
    }
}