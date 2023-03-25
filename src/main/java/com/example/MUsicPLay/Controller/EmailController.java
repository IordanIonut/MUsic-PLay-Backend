package com.example.MUsicPLay.Controller;

import com.example.MUsicPLay.Model.EmailDTO;
import com.example.MUsicPLay.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody EmailDTO emailDTO) throws MessagingException, IOException {
        emailService.sendEmail(
                emailDTO.getName(),
                emailDTO.getEmail(),
                emailDTO.getSubject(),
                emailDTO.getMessage());
    }
}