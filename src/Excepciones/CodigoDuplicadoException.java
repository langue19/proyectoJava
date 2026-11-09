/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

public class CodigoDuplicadoException extends Exception{
    
    private static final long serialVersionUID = 1L;

    private final String entidad;
    private final String codigo;

    /**
     * @param entidad que se intentaba registrar: "vehiculo" o "mision"
     * @param codigo el codigo repetido
     */
    public CodigoDuplicadoException(String entidad, String codigo) {
        super("El codigo " + codigo + " ya esta registrado como " + entidad + ".");
        this.entidad = entidad;
        this.codigo = codigo;
    }

    public String getEntidad() {
        return entidad;
    }

    public String getCodigo() {
        return codigo;
    }
}
