package com.example.Emaildemo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message,MultipartFile file) throws MessagingException;

}
