package BiagioCota.payloads.prenotazione;

import BiagioCota.entities.Prenotazione;

import java.time.LocalDateTime;
import java.util.UUID;

public record PrenotazioneResponse(UUID id, UUID eventoId, String eventoTitle,
                                   UUID utenteId, String utenteUsername,
                                   LocalDateTime dataPrenotazione) {

    public static PrenotazioneResponse from(Prenotazione prenotazione) {
        return new PrenotazioneResponse(
            prenotazione.getId(),
            prenotazione.getEvento().getId(),
            prenotazione.getEvento().getTitle(),
            prenotazione.getUtente().getId(),
            prenotazione.getUtente().getUsername(),
            prenotazione.getDataPrenotazione()
        );
    }
}
