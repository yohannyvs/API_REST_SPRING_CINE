
package com.cine.rest_spring.service;

import com.cine.rest_spring.model.pelicula;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface peliculaServiceInterface
{
    List<pelicula> getpeliculas() throws SQLException, ClassNotFoundException;
    String add_pelicula(String nombre, String categoria, String idioma, String img) throws ClassNotFoundException, SQLException;
    String add_presentacion(int id_pelicula, String hora, String sala) throws ClassNotFoundException, SQLException;
    List<pelicula> getpeliculascategoria(String categoria) throws SQLException, ClassNotFoundException;
    List<pelicula> getpeliculasidioma(String idioma) throws SQLException, ClassNotFoundException;
    List<pelicula> getpeliculasnombre(String nombre) throws SQLException, ClassNotFoundException;
    String compra_taquilla(String id_admin, int id_presentacion, int aciento, String cedula) throws ClassNotFoundException, SQLException;
    String pago(int num_factura, int num_tarjeta, String fecha, String num_seguridad) throws ClassNotFoundException, SQLException;
    List<String> getacientos_ocupado( int id_presentacion ) throws SQLException, ClassNotFoundException;
}
