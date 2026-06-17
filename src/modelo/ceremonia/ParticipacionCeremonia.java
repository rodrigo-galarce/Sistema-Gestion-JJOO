package modelo.ceremonia;

import modelo.persona.Persona;

import java.io.Serializable;

public class ParticipacionCeremonia {
    private Persona persona;
    private Ceremonia ceremonia;
    private RolCeremonia rol;

    public ParticipacionCeremonia(Persona persona, Ceremonia ceremonia, RolCeremonia rol) {
        this.persona = persona;
        this.ceremonia = ceremonia;
        this.rol = rol;
    }

    public Persona getPersona() {
        return persona;
    }

    public Ceremonia getCeremonia() {
        return ceremonia;
    }

    public RolCeremonia getRol() {
        return rol;
    }
}
