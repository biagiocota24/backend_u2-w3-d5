package BiagioCota.repositories;

import BiagioCota.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    List<Prenotazione> findByUtenteId(UUID utenteId);
    long countByEventoId(UUID eventoId);
    boolean existsByEventoIdAndUtenteId(UUID eventoId, UUID utenteId);
}
