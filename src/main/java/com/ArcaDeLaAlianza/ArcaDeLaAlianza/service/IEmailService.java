package com.ArcaDeLaAlianza.ArcaDeLaAlianza.service;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.service.models.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {
    public void sendEmail (EmailDTO email) throws MessagingException;
}
