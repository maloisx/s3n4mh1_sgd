package com.senamhi.sis.dto;

public class UmbralesPorEstacion {

    private String codEstacion;
    private Integer codVariable;
    private Double nr1;
    private Double nr2;
    private String codFlagData;

    public UmbralesPorEstacion() {
    }

    public UmbralesPorEstacion(String codEstacion, Integer codVariable, Double nr1, Double nr2, String codFlagData) {
        this.codEstacion = codEstacion;
        this.codVariable = codVariable;
        this.nr1 = nr1;
        this.nr2 = nr2;
        this.codFlagData = codFlagData;
    }

    public String getCodEstacion() {
        return codEstacion;
    }

    public void setCodEstacion(String codEstacion) {
        this.codEstacion = codEstacion;
    }

    public Integer getCodVariable() {
        return codVariable;
    }

    public void setCodVariable(Integer codVariable) {
        this.codVariable = codVariable;
    }

    public Double getNr1() {
        return nr1;
    }

    public void setNr1(Double nr1) {
        this.nr1 = nr1;
    }

    public Double getNr2() {
        return nr2;
    }

    public void setNr2(Double nr2) {
        this.nr2 = nr2;
    }

    public String getCodFlagData() {
        return codFlagData;
    }

    public void setCodFlagData(String codFlagData) {
        this.codFlagData = codFlagData;
    }

    @Override
    public String toString() {
        return "UmbralesPorEstacion{" + "codEstacion=" + codEstacion + ", codVariable=" + codVariable + ", nr1=" + nr1 + ", nr2=" + nr2 + ", codFlagData=" + codFlagData + '}';
    }


    
}
