package LogicaNegocios;

import AccesoDatos.Archivos.*;

public class ConversorVehiculos {

    public RegistroVehiculo aRegistro(VehiculoAutonomo v) {

        return new RegistroVehiculo(
                Integer.parseInt(v.getCod_ident()),
                (byte) v.getCodigoTipo(),
                v.getModelo(),
                v.getNivel_bat(),
                v.getCapacidad_maxima(),
                v.getKm_reco(),
                (byte) v.getEstado(),
                v.getDatoNumerico1(),
                v.getDatoNumerico2(),
                v.getDatoTexto()
        );
    }

    public VehiculoAutonomo aVehiculo(RegistroVehiculo r) {
        VehiculoAutonomo v;
        switch (r.getTipoVehiculo()) {
            case RegistroVehiculo.DRON:
                DronAereo dron = new DronAereo();
                dron.validarDatosBase(
                        String.valueOf(r.getCodigo()),
                        r.getModelo(),
                        (int) r.getNivelBateria(),
                        (int) r.getCapacidadCarga(),
                        r.getKilometrosRecorridos(),
                        r.getEstadoOperativo()
                );
                dron.validarDatosDron(
                        r.getDatoNumerico1(),
                        r.getDatoNumerico2()
                );
                v = dron;
                break;
            case RegistroVehiculo.ROBOT:
                Robot robot = new Robot();
                robot.validarDatosBase(
                        String.valueOf(r.getCodigo()),
                        r.getModelo(),
                        (int) r.getNivelBateria(),
                        (int) r.getCapacidadCarga(),
                        r.getKilometrosRecorridos(),
                        r.getEstadoOperativo()
                );
                robot.validarDatosRobot(
                        (int) r.getDatoNumerico2(),
                        r.getDatoNumerico1()
                );
                v = robot;
                break;
            case RegistroVehiculo.ELECTRICO:
                VehiculoElectrico electrico = new VehiculoElectrico();
                electrico.validarDatosBase(
                        String.valueOf(r.getCodigo()),
                        r.getModelo(),
                        (int) r.getNivelBateria(),
                        (int) r.getCapacidadCarga(),
                        r.getKilometrosRecorridos(),
                        r.getEstadoOperativo()
                );
                electrico.validarDatosElectrico(
                        r.getDatoNumerico1(),
                        r.getDatoTexto()
                );
                v = electrico;
                break;
            default:
                throw new IllegalArgumentException("Tipo de vehículo desconocido");
        }
        return v;
    }
}
