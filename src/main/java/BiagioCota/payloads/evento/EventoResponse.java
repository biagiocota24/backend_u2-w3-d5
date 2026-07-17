package BiagioCota.payloads.evento;

import BiagioCota.entities.Evento;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventoResponse(UUID id, String title, String description, LocalDateTime date,
                             String location, int maxCapacity,
                             UUID utenteCreatoreId, String eventCreatorUsername) {

    public static EventoResponse from(Evento evento) {
        return new EventoResponse(
                evento.getId(),
                evento.getTitle(),
                evento.getDescription(),
                evento.getDate(),
                evento.getLocation(),
                evento.getMaxCapacity(),
                evento.getEventCreator().getId(),
                evento.getEventCreator().getUsername()
        );
    }
}
