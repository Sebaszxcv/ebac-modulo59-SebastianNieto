package com.ebac.modulo59.models;

public class DireccionModel {
    private Integer idDireccion;
    private Integer idUsuario;
    private String calle;
    private Integer numero;
    private String estado;

    public DireccionModel() {}

    public DireccionModel(Integer idDireccion, Integer idUsuario, String calle, Integer numero, String estado) {
        this.idDireccion = idDireccion;
        this.idUsuario = idUsuario;
        this.calle = calle;
        this.numero = numero;
        this.estado = estado;
    }

    public Integer getIdDireccion() { return idDireccion; }
    public void setIdDireccion(Integer idDireccion) { this.idDireccion = idDireccion; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "DireccionModel{" +
                "idDireccion=" + idDireccion +
                ", idUsuario=" + idUsuario +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", estado='" + estado + '\'' +
                '}';
    }
}
