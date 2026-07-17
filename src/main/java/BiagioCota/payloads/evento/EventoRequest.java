package BiagioCota.payloads.evento;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoRequest {

    @NotBlank(message = "Titolo obbligatorio")
    private String title;

    private String description;

    @NotNull(message = "Data obbligatoria")
    @Future(message = "La data dell'evento deve essere nel futuro")
    private LocalDateTime date;

    @NotBlank(message = "Luogo obbligatorio")
    private String location;

    @NotNull(message = "Capacità massima obbligatoria")
    @Min(value = 1, message = "La capacità deve essere almeno 1")
    private Integer maxCapacity;
}
