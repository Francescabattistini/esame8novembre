package francescabattistini.esame8novembre.payload;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorwhitListDto(
        String message,
        LocalDateTime timestamp,
        Map<String, String> errors) {
}
