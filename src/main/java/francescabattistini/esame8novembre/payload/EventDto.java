package francescabattistini.esame8novembre.payload;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record EventDto(
        @NotEmpty(message = "Il nome del'evento è obbligatorio!")
        String eventName,
        @NotEmpty(message = "la descrizione dell'evento è obbligatoria!")
        String descriptionEvent,
        @NotEmpty(message = "la data evento è obbligatoria!")
        LocalDate dateEvent,
        @NotEmpty(message = "La location è oblicatoria!")
        String locationEvento,
        @NotEmpty(message = "il numero partecipanti è obligatorio")
        Integer numPlaesAvabile,
        @NotEmpty(message = "id manager obligatorio")
        String idManager



) {
}
