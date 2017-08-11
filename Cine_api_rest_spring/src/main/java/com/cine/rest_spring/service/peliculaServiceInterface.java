
package com.cine.rest_spring.service;

import com.cine.rest_spring.model.pelicula;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface peliculaServiceInterface
{
    List<pelicula> getpeliculas() throws SQLException, ClassNotFoundException;
    void add_pelicula(String nombre, String categoria, String idioma, String img) throws ClassNotFoundException, SQLException;
    void add_presentacion(int id_pelicula, String hora, String sala) throws ClassNotFoundException, SQLException;
}
