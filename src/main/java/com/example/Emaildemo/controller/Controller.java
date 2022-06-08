package com.example.Emaildemo.controller;

import com.example.Emaildemo.resource.EmailMessage;
import com.example.Emaildemo.service.EmailSenderService;
import com.example.Emaildemo.service.StringToEmailConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.Date;

@RestController
public class Controller {
    @Autowired
    private final EmailSenderService emailSenderService;
    @Autowired
    private StringToEmailConverter stringToEmailConverter;

    public Controller(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }


    @PostMapping("/send-attachment")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity sendAttachment(@RequestParam("attachment") MultipartFile file,@RequestParam("message") String email  ) throws MessagingException {
        EmailMessage emailMessage = stringToEmailConverter.convert(email);
        emailSenderService.sendEmail(emailMessage.getTo(),emailMessage.getSubject(),emailMessage.getMessage(),file);
        return ResponseEntity.ok("email was send");
    }

}
