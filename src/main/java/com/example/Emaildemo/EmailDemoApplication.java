package com.example.Emaildemo;

import com.example.Emaildemo.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication

public class EmailDemoApplication {
	@Autowired
	private final EmailSenderService emailSenderService;

	public EmailDemoApplication(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}

	public static void main(String[] args) {
		SpringApplication.run(EmailDemoApplication.class, args);
	}

}
