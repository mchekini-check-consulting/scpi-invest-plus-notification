package fr.formationacademy.scpiinvestplusnotification.dto;

import fr.formationacademy.scpiinvestplusnotification.enums.BodyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDtoIn {
    @Schema(description = "Sender's email address", example = "sender@example.com")
    @NotBlank(message = "Sender email (from) is required")
    @Email(message = "Invalid email format for sender (from)")
    private String from;

    @Schema(description = "Recipient's email address", example = "recipient@example.com")
    @NotBlank(message = "Recipient email (to) is required")
    @Email(message = "Invalid email format for recipient (to)")
    private String to;

    @Schema(description = "Subject of the email", example = "Welcome to SCPI Invest Plus")
    @NotBlank(message = "Subject is required")
    private String subject;

    @Schema(description = "Content of the email, can be plain text or an HTML template including the CSS.", example = """
            HTML: <html>
            <head>
                <title>Email Subject</title>
                <style>
                    body { font-family: Arial, sans-serif; color: #333; }
                    h1 { color: #2c3e50; }
                </style>
            </head>
            <body>
                <h1>Email Subject</h1>
                <p>Dear user, your request has been processed successfully.</p>
            </body>
            </html>
            """
    )
    @NotBlank(message = "Email body cannot be empty")
    private String body;

    @Schema(description = "Format of the email body (TEXT or HTML)", example = "TEXT")
    @NotNull(message = "BodyType (TEXT or HTML) is required")
    private BodyType bodyType;
}
