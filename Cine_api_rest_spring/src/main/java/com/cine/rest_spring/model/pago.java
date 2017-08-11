
package com.cine.rest_spring.model;

public class pago 
{
    private int num_factura;
    private int num_tarjeta;
    private String fecha;
    private String num_seguridad;

    public pago() { }

    public pago(int num_factura, int num_tarjeta, String fecha, String num_seguridad) {
        this.num_factura = num_factura;
        this.num_tarjeta = num_tarjeta;
        this.fecha = fecha;
        this.num_seguridad = num_seguridad;
    }

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public int getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(int num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNum_seguridad() {
        return num_seguridad;
    }

    public void setNum_seguridad(String num_seguridad) {
        this.num_seguridad = num_seguridad;
    }
    
}
