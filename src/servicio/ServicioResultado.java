package servicio;

import modelo.SistemaJJOO;
import modelo.marca.Marca;
import modelo.persona.Atleta;
import modelo.resultado.Medalla;
import modelo.resultado.Resultado;
import modelo.resultado.TipoMedalla;
import modelo.deporte.Competencia;

import java.util.Collections;

public class ServicioResultado {
    private SistemaJJOO sistema;

    public ServicioResultado(SistemaJJOO sistema) {
        this.sistema = sistema;
    }

    public void registrarResultado(Competencia competencia, Atleta atleta, Marca marca) {
        Resultado resultado = new Resultado(0, atleta, marca);              //posicion 0 porque despues se calcula automaticamente
        competencia.agregarResultado(resultado);
        calcularPosiciones(competencia);
        asignarMedallas(competencia);
        competencia.getDisciplina().actualizarRecord(resultado);
    }

        public void calcularPosiciones(Competencia competencia) {
        Collections.sort(competencia.getListaResultados());
        int posicion = 1;
        for (Resultado r : competencia.getListaResultados()) {
            r.setPosicion(posicion);posicion++;
        }
    }

    public void asignarMedallas(Competencia competencia) {
        for (Resultado r : competencia.getListaResultados()) {
            Atleta atleta = r.getAtleta();
            if (r.getPosicion() == 1 && !tieneMedallaCompetencia(atleta, competencia)) {
                Medalla oro = new Medalla(TipoMedalla.ORO, atleta, competencia);
                atleta.agregarMedalla(oro);
            }

            else if (r.getPosicion() == 2 && !tieneMedallaCompetencia(atleta, competencia)) {
                Medalla plata = new Medalla(TipoMedalla.PLATA, atleta, competencia);
                atleta.agregarMedalla(plata);
            }

            else if (r.getPosicion() == 3 && !tieneMedallaCompetencia(atleta, competencia)){
                Medalla bronce = new Medalla(TipoMedalla.BRONCE, atleta, competencia);
                atleta.agregarMedalla(bronce);
            }
        }
    }

    private boolean tieneMedallaCompetencia(Atleta atleta, Competencia competencia) {
        for (Medalla medalla : atleta.getListaMedallas()) {
            if (medalla.getCompetencia().equals(competencia)) {
                return true;
            }
        }
        return false;
    }
}