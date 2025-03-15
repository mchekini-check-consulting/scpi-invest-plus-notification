package fr.formationacademy.scpiinvestplusnotification.resource;

import fr.formationacademy.scpiinvestplusnotification.dto.EmailDtoIn;
import fr.formationacademy.scpiinvestplusnotification.service.EmailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/email")
@Slf4j
public class EmailResource {
    private final EmailService emailService;

    public EmailResource(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("send")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody EmailDtoIn email) {
        this.emailService.sendEmail(email);
        return ResponseEntity.ok("Email sent successfully to " + email.getTo());
    }
}
