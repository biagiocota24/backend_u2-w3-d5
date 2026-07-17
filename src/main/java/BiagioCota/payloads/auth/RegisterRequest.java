package BiagioCota.payloads.auth;

import BiagioCota.enums.RoleUser;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username obbligatorio")
    private String username;

    @NotBlank(message = "Nome obbligatorio")
    private String name;

    @NotBlank(message = "Cognome obbligatorio")
    private String surname;


    private LocalDate dateOfBirth;

    @NotNull(message = "Ruolo obbligatorio")
    private RoleUser role;

    @NotBlank(message = "Email obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;

    @NotBlank(message = "Password obbligatoria")
    @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
    private String password;
}
