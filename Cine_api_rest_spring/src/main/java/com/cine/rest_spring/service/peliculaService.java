
package com.cine.rest_spring.service;

import com.cine.rest_spring.model.pelicula;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("peliculaserviceinterface")
@Transactional
public class peliculaService implements peliculaServiceInterface
{
    @Override
    public List<pelicula> getpeliculas() throws SQLException, ClassNotFoundException
    {
        Connection cn = conectar.con();
        List<pelicula> list = new ArrayList();
        String query="SELECT presentacion.id_presentacion, pelicula.nombre, pelicula.categoria, pelicula.idioma,horario.hora,sala.numero_sala FROM pelicula inner join presentacion on pelicula.id_peli=presentacion.id_pelicula inner join horario on horario.id_hora = presentacion.id_horario inner join sala on sala.id_sala=presentacion.id_sala";
        Statement stmt = cn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) 
        {
            list.add(new pelicula(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            ));
        }
        
        return list;
    }
    
}
