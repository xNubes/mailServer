package com.springframework.boot.springbootstartermail;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("mail-sender")
@RestController
public class EmailSender {
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender sender;

    @PostMapping("send-mail")
    public ResponseEntity<Details> sendMail(@RequestBody Details details) {

        MimeMessage message = sender.createMimeMessage();

        try {
            message.setFrom(new InternetAddress("troumble@gmail.com", "TerminHelper"));
            message.setRecipients(Message.RecipientType.TO, details.receiver());
            message.setSubject(details.subject());

            Map<String, Object> model = new HashMap<>();
            model.put("name", details.name());
            model.put("email", details.email());
            model.put("message", details.message());

            Context context = new Context();
            context.setVariables(model);
            String html = templateEngine.process("sampleEmail", context);

            message.setContent(html, "text/html; charset=UTF-8");
            sender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(details);
        }
        return ResponseEntity.ok(details);
    }
}

