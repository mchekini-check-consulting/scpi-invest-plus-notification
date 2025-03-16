package fr.formationacademy.scpiinvestplusnotification.service;

import fr.formationacademy.scpiinvestplusnotification.dto.EmailDtoIn;
import fr.formationacademy.scpiinvestplusnotification.enums.BodyType;
import fr.formationacademy.scpiinvestplusnotification.globalExceptionHandler.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
@Slf4j
public class EmailService {

    private final SesClient sesClient;

    public EmailService() {
        this.sesClient = SesClient.builder().build();
    }

    public void sendEmail(EmailDtoIn email) throws GlobalException {
        log.info("Preparing to send email to {}", email.getTo());

        Content content = Content.builder()
                .data(email.getBody())
                .charset("UTF-8")
                .build();

        Body body;
        if (email.getBodyType() == BodyType.HTML) {
            log.info("Email body type: HTML");
            body = Body.builder().html(content).build();
        } else {
            log.info("Email body type: TEXT");
            body = Body.builder().text(content).build();
        }

        Destination destination = Destination.builder().toAddresses(email.getTo()).build();
        Message message = Message.builder()
                .subject(Content.builder().data(email.getSubject()).build())
                .body(body)
                .build();

        SendEmailRequest request = SendEmailRequest.builder()
                .destination(destination)
                .message(message)
                .source(email.getFrom())
                .build();

        log.debug("Sending email {}", email);
        log.info("Sending email with subject: '{}', from: '{}', to: '{}'",
                email.getSubject(), email.getFrom(), email.getTo());

        try {
            SendEmailResponse response = sesClient.sendEmail(request);
            log.info("Email sent successfully! Message ID: {}", response.messageId());
        } catch (SesException e) {
            throw new GlobalException(HttpStatus.valueOf(e.statusCode()), e.getMessage());
        }
    }
}
