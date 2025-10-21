package com.ebac.modulo59.models;

public class DireccionModel {
    // Cambiado a Integer para permitir null
    private Integer id;
    private String ciudad;
    private String pais;

    public DireccionModel() {}

    public DireccionModel(String ciudad, String pais) {
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public DireccionModel(Integer id, String ciudad, String pais) {
        this.id = id;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    @Override
    public String toString() {
        return "DireccionModel{id=" + id + ", ciudad='" + ciudad + "', pais='" + pais + "'}";
    }
}
