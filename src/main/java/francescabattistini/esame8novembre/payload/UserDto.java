package francescabattistini.esame8novembre.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserDto(
        @NotEmpty(message = "Il nome è obbligatorio!")
        @Size(min = 2, max = 10, message = "Il nome proprio deve essere compreso tra 2 e 10 caratteri!")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 2, max = 15, message = "Il cognome deve essere compreso tra 2 e 15 caratteri!")
        String surname,
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Email(message = "L'email inserita non è un'email valida")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        @Size(min = 8,max =15, message = "La password deve avere almeno 4 caratteri!")
        String password,
        @NotEmpty(message = "devi scegliere un ruolo tra Manager o User")
        String ruolo
  ){}

