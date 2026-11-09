/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

public class VehiculoNoEncontradoException extends Exception{
    
    private static final long serialVersionUID = 1L;

    private final String codigo;

    public VehiculoNoEncontradoException(String codigo) {
        super("No existe un vehiculo con el codigo " + codigo + ".");
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
