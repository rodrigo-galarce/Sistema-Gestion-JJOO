package excepciones;

public class InscripcionDuplicadaException extends RuntimeException {
    public InscripcionDuplicadaException(String message) {
        super(message);
    }
}
