package BiagioCota.services;

import BiagioCota.entities.Evento;
import BiagioCota.entities.User;
import BiagioCota.enums.RoleUser;
import BiagioCota.exceptions.EventoNotFoundException;
import BiagioCota.exceptions.NonAutorizzatoException;
import BiagioCota.payloads.evento.EventoRequest;
import BiagioCota.payloads.evento.EventoResponse;
import BiagioCota.repositories.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoResponse create(EventoRequest request, User currentUser) {
//        if (currentUser.getRole() != RoleUser.EVENT_CREATOR) {
//            throw new NonAutorizzatoException("Solo gli organizzatori possono creare eventi");
//        }
        Evento evento = new Evento();
        evento.setTitle(request.getTitle());
        evento.setDescription(request.getDescription());
        evento.setDate(request.getDate());
        evento.setLocation(request.getLocation());
        evento.setMaxCapacity(request.getMaxCapacity());
        evento.setEventCreator(currentUser);
        return EventoResponse.from(eventoRepository.save(evento));
    }

    public EventoResponse getById(UUID id) {
        return EventoResponse.from(
                eventoRepository.findById(id).orElseThrow(() -> new EventoNotFoundException(id))
        );
    }

    private void verifiryCreator(Evento evento, User currentUser) {
        if (currentUser.getRole() != RoleUser.EVENT_CREATOR) {
            throw new NonAutorizzatoException("Solo gli organizzatori possono modificare o eliminare eventi");
        }
        if (!evento.getEventCreator().getId().equals(currentUser.getId())) {
            throw new NonAutorizzatoException("Non sei il creatore di questo evento");
        }
    }


    public List<EventoResponse> getAll() {
        return eventoRepository.findAll().stream()
                .map(EventoResponse::from)
                .toList();
    }


    public EventoResponse update(UUID id, EventoRequest request, User currentUser) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EventoNotFoundException(id));
        verifiryCreator(evento, currentUser);
        evento.setTitle(request.getTitle());
        evento.setDescription(request.getDescription());
        evento.setDate(request.getDate());
        evento.setLocation(request.getLocation());
        evento.setMaxCapacity(request.getMaxCapacity());
        return EventoResponse.from(eventoRepository.save(evento));
    }


    public void delete(UUID id, User currentUser) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EventoNotFoundException(id));
        verifiryCreator(evento, currentUser);
        eventoRepository.delete(evento);
    }

}
