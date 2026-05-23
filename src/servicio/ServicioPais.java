package servicio;

import excepciones.PaisDuplicadoException;
import modelo.SistemaJJOO;
import modelo.pais.Pais;

public class ServicioPais {
    private SistemaJJOO sistema;

    public ServicioPais(SistemaJJOO sistema) {
        this.sistema = sistema;
    }

    public void registrarPais(String nombre,String codigo) throws PaisDuplicadoException {
        if (sistema.getListaPaises().containsKey(codigo)) {
            throw new PaisDuplicadoException("Error. Ese país ya se encuentra registrado");
        }
        Pais pais = new Pais(nombre, codigo);
        sistema.agregrarPais(pais);
    }

}
