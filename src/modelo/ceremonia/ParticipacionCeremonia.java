package modelo.ceremonia;

import java.io.Serializable;

public class ParticipacionCeremonia implements Serializable {
    private RolCeremonia rol;

    public  ParticipacionCeremonia(RolCeremonia rol) {
        this.rol = rol;
    }

    public RolCeremonia getRol() {
        return rol;
    }
}
