package BiagioCota.exceptions;

public class PostiEsauritiException extends RuntimeException {
    public PostiEsauritiException() {
        super("Posti esauriti per questo evento");
    }
}
