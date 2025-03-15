package fr.formationacademy.scpiinvestplusnotification.globalExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTemplate {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private List<ValidationError> errors;
}
