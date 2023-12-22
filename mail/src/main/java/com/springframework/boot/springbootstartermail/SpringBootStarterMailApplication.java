package com.springframework.boot.springbootstartermail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringBootStarterMailApplication {

	@Autowired
	private EmailSender senderService;

	private Details details;


	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterMailApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendEmail() throws Exception {
		EmailSender.sendMail(details);
	}
}
