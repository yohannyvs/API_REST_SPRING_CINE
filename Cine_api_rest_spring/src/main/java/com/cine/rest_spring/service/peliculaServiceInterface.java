
package com.cine.rest_spring.service;

import com.cine.rest_spring.model.pelicula;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface peliculaServiceInterface
{
    List<pelicula> getpeliculas() throws SQLException, ClassNotFoundException;
}
