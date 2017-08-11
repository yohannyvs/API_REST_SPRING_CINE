
package com.cine.rest_spring.model;

public class compra_taquilla 
{
    private String id_admin;
    private int id_presentacion;
    private int aciento;
    private String cedula;

    public compra_taquilla() {
    }

    public compra_taquilla(String id_admin, int id_presentacion, int aciento, String cedula) {
        this.id_admin = id_admin;
        this.id_presentacion = id_presentacion;
        this.aciento = aciento;
        this.cedula = cedula;
    }

    public String getId_admin() {
        return id_admin;
    }

    public void setId_admin(String id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_presentacion() {
        return id_presentacion;
    }

    public void setId_presentacion(int id_presentacion) {
        this.id_presentacion = id_presentacion;
    }

    public int getAciento() {
        return aciento;
    }

    public void setAciento(int aciento) {
        this.aciento = aciento;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
        
}
