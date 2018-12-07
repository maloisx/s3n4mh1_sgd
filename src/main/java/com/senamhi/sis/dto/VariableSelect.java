package com.senamhi.sis.dto;

public class VariableSelect {

    private String codigo;
    private String nombre;

    public VariableSelect() {
    }

    public VariableSelect(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "EstacionSelect{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }
    
}
