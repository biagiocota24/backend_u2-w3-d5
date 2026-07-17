package BiagioCota.errorHandler;

import BiagioCota.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EventoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEventoNotFound(EventoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of(404, ex.getMessage()));
    }

    @ExceptionHandler(PrenotazioneNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePrenotazioneNotFound(PrenotazioneNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of(404, ex.getMessage()));
    }

    @ExceptionHandler(NonAutorizzatoException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(NonAutorizzatoException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(ErrorResponse.of(403, ex.getMessage()));
    }

    @ExceptionHandler(PostiEsauritiException.class)
    public ResponseEntity<ErrorResponse> handlePostiEsauriti(PostiEsauritiException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErrorResponse.of(409, ex.getMessage()));
    }

    @ExceptionHandler(PrenotazioneGiaEsistenteException.class)
    public ResponseEntity<ErrorResponse> handlePrenotazioneGiaEsistente(PrenotazioneGiaEsistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErrorResponse.of(409, ex.getMessage()));
    }

    @ExceptionHandler(UserEsisteGiaException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserEsisteGiaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErrorResponse.of(409, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String messages = ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .collect(Collectors.joining("; "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of(400, messages));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse.of(500, "Errore interno del server"));
    }
}
