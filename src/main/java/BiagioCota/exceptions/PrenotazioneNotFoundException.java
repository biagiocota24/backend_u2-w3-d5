package BiagioCota.exceptions;

import java.util.UUID;

public class PrenotazioneNotFoundException extends RuntimeException {
    public PrenotazioneNotFoundException(UUID id) {
        super("Prenotazione non trovata con id: " + id);
    }
}
