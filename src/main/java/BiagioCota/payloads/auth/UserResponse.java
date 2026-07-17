package BiagioCota.payloads.auth;

import BiagioCota.entities.User;
import BiagioCota.enums.RoleUser;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(UUID id, String username, String name, String surname,
                           LocalDate dateOfBirth, RoleUser role, String email) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getEmail()
        );
    }
}
