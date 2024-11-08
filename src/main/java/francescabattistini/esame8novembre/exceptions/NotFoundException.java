package francescabattistini.esame8novembre.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
	public NotFoundException(UUID id) {
		super("L'elemento con  " + id + " non è stato trovato!");
	}

	public NotFoundException(String msg) {
		super(msg);
	}
}
