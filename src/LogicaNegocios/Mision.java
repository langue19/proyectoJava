package LogicaNegocios;

public class Mision {

    private String cod_mision;
    private String origen;
    private String destino;
    private int distancia;
    private double peso;
    private int prioridad; //1-Alta 2-Media 3-Baja 
    private int estado; // 1-Pendiente 2-Asignado 3-Finalizado

    public Mision() {
        this.cod_mision = "";
        this.origen = "";
        this.destino = "";
        this.distancia = 0;
        this.peso = 0.0;
        this.prioridad = 0;
        this.estado = 0;
    }
    public void validarMision(String cod_mision, String origen, String destino,
            int distancia, double peso, int prioridad, int estado) {
        setCod_mision(cod_mision);
        validarCod(cod_mision);
        validarOrigen(origen);
        validarDestino(destino);
        validarDistancia(distancia);
        validarPeso(peso);
        validarPrioridad(prioridad);
        validarEstado(estado);
    }
    
    
    public void validarCod(String codigo){
        if(codigo==null || codigo.trim().isEmpty()){
            throw new IllegalArgumentException("El codigo no puede estar vacío.");
        }
        setCod_mision(codigo);
    }
    
    public void validarOrigen(String origen){
        if(origen==null || origen.trim().isEmpty()){
            throw new IllegalArgumentException("El origen no puede estar vacío.");
        }
        setOrigen(origen);
    }
    
    public void validarDestino(String destino){
        if(destino==null || destino.trim().isEmpty()){
            throw new IllegalArgumentException("El destino no puede estar vacío.");
        }
        setDestino(destino);
    }
    
    public void validarDistancia(int distancia){
        if(distancia <= 0 ){
            throw new IllegalArgumentException("La distancia debe ser mayor a 0.");
        }
        setDistancia(distancia);
    }
    
    public void validarPeso(Double peso){
        if(peso<0.0){
            throw new IllegalArgumentException("El peso no puede ser negativo.");
        }
        setPeso(peso);
    }
    
     private void validarPrioridad(int prioridad) {
        if (prioridad != 1 && prioridad != 2 && prioridad!=3) {
            throw new IllegalArgumentException("La prioridad debe ser 1 (alta), 2 (media) o 3(baja).");
        }
        setPrioridad(prioridad);
    }
     
     private void validarEstado(int estado) {
        if (estado != 1 && estado != 2 && estado!=3) {
            throw new IllegalArgumentException("El estado debe ser 1 (pendiente), 2 (asignado) o 3(finalizado).");
        }
        setEstado(estado);
    }
    
    
    public String getCod_mision() {
        return cod_mision;
    }

    public void setCod_mision(String cod_mision) {
        this.cod_mision = cod_mision;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {

        String estadoTexto;
        String prioridadTexto;
        switch (estado) {
            case 1:
                estadoTexto = "Pendiente";
                break;
            case 2:
                estadoTexto = "Asignado";
                break;
            case 3:
                estadoTexto = "Finalizado";
                break;
            default:
                estadoTexto = "Desconocido";
        }
        switch (prioridad) {
            case 1:
                prioridadTexto = "Alta";
                break;
            case 2:
                prioridadTexto = "Media";
                break;
            case 3:
                prioridadTexto = "Baja";
                break;
            default:
                prioridadTexto = "Desconocida";
        }
        return "\nMisión: " + cod_mision
                + " | Origen: " + origen
                + " | Destino: " + destino
                + " | Distancia: " + distancia + " km"
                + " | Peso: " + peso + " kg"
                + " | Prioridad: " + prioridadTexto
                + " | Estado: " + estadoTexto;
    }
}
