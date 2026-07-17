package BiagioCota.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
    name = "prenotazioni",
    uniqueConstraints = @UniqueConstraint(columnNames = {"utente_id", "evento_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utente_id", nullable = false)
    private User utente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataPrenotazione;

    @PrePersist
    protected void onCreate() {
        dataPrenotazione = LocalDateTime.now();
    }
}
