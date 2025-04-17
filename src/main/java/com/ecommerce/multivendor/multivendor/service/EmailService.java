package com.ecommerce.multivendor.multivendor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
private JavaMailSender javaMailSender;
public void sendVerificationOtpEmail(String userEmail,String otp,String subject,String text) throws MessagingException {
	try {
	MimeMessage message=javaMailSender.createMimeMessage();
	MimeMessageHelper helper=new MimeMessageHelper(message,"utf-8");
	helper.setFrom("story000ai@gmail.com");
	helper.setSubject(subject);
	helper.setText(text);
	helper.setTo(userEmail);
    javaMailSender.send(message);		
		
		
		
	} catch (MailException e) {
		throw new MailSendException("failed to send email");
	}
	
	
	
}
}
