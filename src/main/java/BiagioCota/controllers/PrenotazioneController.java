package BiagioCota.controllers;

import BiagioCota.entities.User;
import BiagioCota.payloads.prenotazione.MessageResponse;
import BiagioCota.payloads.prenotazione.PrenotazioneResponse;
import BiagioCota.services.PrenotazioneService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
@AllArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @PostMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponse prenota(@PathVariable UUID eventoId,
                                        @AuthenticationPrincipal User currentUser) {
        return prenotazioneService.prenota(eventoId, currentUser);
    }

    @GetMapping("/mie")
    public List<PrenotazioneResponse> getMiePrenotazioni(@AuthenticationPrincipal User currentUser) {
        return prenotazioneService.getMiePrenotazioni(currentUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse annulla(@PathVariable UUID id, @AuthenticationPrincipal User currentUser) {
        prenotazioneService.annulla(id, currentUser);
        return new MessageResponse("Prenotazione " + id + " annullata");
    }
}
