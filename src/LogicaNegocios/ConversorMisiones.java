package LogicaNegocios;

import AccesoDatos.Archivos.*;

public class ConversorMisiones {

    // GUARDAR: Mision -> RegistroMisiones
    public RegistroMisiones aRegistro(Mision m) {
        return new RegistroMisiones(
                Integer.parseInt(m.getCod_mision()),
                m.getOrigen(),
                m.getDestino(),
                m.getDistancia(),
                m.getPeso(),
                (byte) m.getPrioridad(),
                (byte) m.getEstado()
        );
    }

    // RECUPERAR: RegistroMisiones -> Mision
    public Mision aMision(RegistroMisiones r) {

        Mision m = new Mision();

        m.validarMision(
                String.valueOf(r.getCodigo()),
                r.getOrigen(),
                r.getDestino(),
                r.getDistancia(),
                r.getPeso(),
                r.getPrioridad(),
                r.getEstadoMision()
        );

        return m;
    }
}
