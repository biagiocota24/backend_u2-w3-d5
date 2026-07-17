package BiagioCota.exceptions;

import java.util.UUID;

public class EventoNotFoundException extends RuntimeException {
    public EventoNotFoundException(UUID id) {
        super("Evento non trovato con id: " + id);
    }
}
