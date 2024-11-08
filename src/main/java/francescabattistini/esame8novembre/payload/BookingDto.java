package francescabattistini.esame8novembre.payload;

import jakarta.validation.constraints.NotNull;

public record BookingDto(
        @NotNull(message = "id User obligatorio!")
        Long idUser,
        @NotNull(message = "id Event obligatorio!")
        Long idEvent

) {
}
