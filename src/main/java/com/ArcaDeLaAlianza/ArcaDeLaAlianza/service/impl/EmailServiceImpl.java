package com.ArcaDeLaAlianza.ArcaDeLaAlianza.service.impl;


import com.ArcaDeLaAlianza.ArcaDeLaAlianza.service.IEmailService;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.service.models.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail (EmailDTO email) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email.getDestinatario());
            helper.setSubject(email.getAseunto());

            Context context = new Context();
            context.setVariable("mensaje",email.getMensaje());
            String contentHTML = templateEngine.process("email",context);

            helper.setText(contentHTML,true);
            javaMailSender.send(message);
            //helper.setText(email.getMensaje());
        } catch (Exception e){
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage(), e);
        }

    }
}
