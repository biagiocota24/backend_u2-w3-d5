package BiagioCota.services;

import BiagioCota.entities.Evento;
import BiagioCota.entities.Prenotazione;
import BiagioCota.entities.User;
import BiagioCota.exceptions.*;
import BiagioCota.payloads.prenotazione.PrenotazioneResponse;
import BiagioCota.repositories.EventoRepository;
import BiagioCota.repositories.PrenotazioneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final EventoRepository eventoRepository;


    public PrenotazioneResponse prenota(UUID eventoId, User currentUser) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new EventoNotFoundException(eventoId));

        if (prenotazioneRepository.existsByEventoIdAndUtenteId(eventoId, currentUser.getId())) {
            throw new PrenotazioneGiaEsistenteException();
        }

        if (prenotazioneRepository.countByEventoId(eventoId) >= evento.getMaxCapacity()) {
            throw new PostiEsauritiException();
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(currentUser);
        prenotazione.setEvento(evento);
        return PrenotazioneResponse.from(prenotazioneRepository.save(prenotazione));
    }


    public List<PrenotazioneResponse> getMiePrenotazioni(User currentUser) {
        return prenotazioneRepository.findByUtenteId(currentUser.getId()).stream()
                .map(PrenotazioneResponse::from)
                .toList();
    }


    public void annulla(UUID prenotazioneId, User currentUser) {
        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId)
                .orElseThrow(() -> new PrenotazioneNotFoundException(prenotazioneId));

        if (!prenotazione.getUtente().getId().equals(currentUser.getId())) {
            throw new NonAutorizzatoException("Non puoi annullare la prenotazione di un altro utente");
        }

        prenotazioneRepository.delete(prenotazione);
    }
}
