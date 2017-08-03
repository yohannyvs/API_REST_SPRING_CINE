
package com.cine.rest_spring.model;

public class prueba_model 
{
    private int id;
    private String nombre;
    
    public prueba_model(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public prueba_model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
