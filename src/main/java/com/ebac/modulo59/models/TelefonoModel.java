package com.ebac.modulo59.models;

public class TelefonoModel {
    private Integer idTelefono;
    private Integer idUsuario;
    private String numero;
    private String tipo;

    public TelefonoModel() {}

    public TelefonoModel(Integer idTelefono, Integer idUsuario, String numero, String tipo) {
        this.idTelefono = idTelefono;
        this.idUsuario = idUsuario;
        this.numero = numero;
        this.tipo = tipo;
    }

    public Integer getIdTelefono() { return idTelefono; }
    public void setIdTelefono(Integer idTelefono) { this.idTelefono = idTelefono; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "TelefonoModel{" +
                "idTelefono=" + idTelefono +
                ", idUsuario=" + idUsuario +
                ", numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
