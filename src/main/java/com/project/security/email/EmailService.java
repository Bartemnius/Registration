package com.project.security.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class EmailService implements EmailSender{

    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(String to, String email) {
      try {
          MimeMessage mimeMessage = javaMailSender.createMimeMessage();
          MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
          helper.setText(email, true);
          helper.setTo(to);
          helper.setSubject("Confirm your email");
          helper.setFrom("welcome@aplication.com");
          javaMailSender.send(mimeMessage);

      } catch (MessagingException e) {
          log.error(e.toString());
          throw new IllegalStateException("Failed to send email");
      }
    }
}
