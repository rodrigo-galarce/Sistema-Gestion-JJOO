package excepciones;

public class CompetenciaFueraDeFechaException extends RuntimeException {
    public CompetenciaFueraDeFechaException(String message) {
        super(message);
    }
}
