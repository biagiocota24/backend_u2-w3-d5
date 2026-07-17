package BiagioCota.controllers;

import BiagioCota.entities.User;
import BiagioCota.payloads.evento.EventoRequest;
import BiagioCota.payloads.evento.EventoResponse;
import BiagioCota.services.EventoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventi")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public List<EventoResponse> getAll() {
        return eventoService.getAll();
    }

    @GetMapping("/{id}")
    public EventoResponse getById(@PathVariable UUID id) {
        return eventoService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoResponse create(@RequestBody @Valid EventoRequest request,
                                 @AuthenticationPrincipal User currentUser) {
        return eventoService.create(request, currentUser);
    }

    @PutMapping("/{id}")
    public EventoResponse update(@PathVariable UUID id,
                                 @RequestBody @Valid EventoRequest request,
                                 @AuthenticationPrincipal User currentUser) {
        return eventoService.update(id, request, currentUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id, @AuthenticationPrincipal User currentUser) {
        eventoService.delete(id, currentUser);
    }
}
