package com.springframework.boot.springbootstartermail;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/testapp")
@Controller
public class EmailSender {
    @Autowired
    static SpringTemplateEngine templateEngine;

    @Autowired
    private static JavaMailSender sender;

    @RequestMapping("/getdetails")
    public static @ResponseBody void sendMail(@RequestBody Details details) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<>();
        model.put("name",details.getName());
        model.put("email",details.getEmail());
        model.put("subject",details.getSubject());
        model.put("message",details.getMessage());
        model.put("receiver",details.getReceiver());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("email-template", context);

        helper.setTo(details.getEmail());
        helper.setText(html,true);
        helper.setSubject("Test Mail");
        sender.send(message);

    }
}