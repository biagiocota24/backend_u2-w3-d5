package BiagioCota.repositories;

import BiagioCota.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {
    List<Evento> findByEventCreatorId(UUID utenteCreatoreId);
}
