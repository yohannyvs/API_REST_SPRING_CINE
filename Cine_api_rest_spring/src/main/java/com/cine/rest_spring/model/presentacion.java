
package com.cine.rest_spring.model;

public class presentacion 
{

    private int id_pelicula;
    private String hora;
    private String sala;

    public presentacion() { }

    public presentacion(int id_pelicula, String hora, String sala) {
        this.id_pelicula = id_pelicula;
        this.hora = hora;
        this.sala = sala;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
    
}
