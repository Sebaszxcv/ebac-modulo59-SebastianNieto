package com.ebac.modulo59.models;

public class UsuarioModel {
    private Integer idUsuario;
    private String nombre;
    private Integer edad;

    public UsuarioModel() {}

    public UsuarioModel(Integer idUsuario, String nombre, Integer edad) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
