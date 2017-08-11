
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
        String query="SELECT presentacion.id_presentacion, pelicula.nombre, pelicula.categoria, pelicula.idioma,horario.hora,sala.numero_sala, pelicula.imagen FROM pelicula inner join presentacion on pelicula.id_peli=presentacion.id_pelicula inner join horario on horario.id_hora = presentacion.id_horario inner join sala on sala.id_sala=presentacion.id_sala";
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
                    rs.getString(6),
                    rs.getString(7)
            ));
        }
        
        return list;
    }
    
    @Override
    public List<pelicula> getpeliculascategoria(String categoria) throws SQLException, ClassNotFoundException
    {
        Connection cn = conectar.con();
        List<pelicula> list = new ArrayList();
        
        CallableStatement cs = null;
        cs= cn.prepareCall("{call busca_peli_caategoria (?)}");
        cs.setString(1, categoria);
        
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) 
        {
            list.add(new pelicula(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
            ));
        }
        
        return list;
    }
    
    @Override
    public List<pelicula> getpeliculasidioma(String idioma) throws SQLException, ClassNotFoundException
    {
        Connection cn = conectar.con();
        List<pelicula> list = new ArrayList();
        
        CallableStatement cs = null;
        cs= cn.prepareCall("{call busca_peli_idioma (?)}");
        cs.setString(1, idioma);
        
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) 
        {
            list.add(new pelicula(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
            ));
        }
        
        return list;
    }
    
    @Override
    public List<pelicula> getpeliculasnombre(String nombre) throws SQLException, ClassNotFoundException
    {
        Connection cn = conectar.con();
        List<pelicula> list = new ArrayList();
        
        CallableStatement cs = null;
        cs= cn.prepareCall("{call busca_peli_nombre (?)}");
        cs.setString(1, nombre);
        
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) 
        {
            list.add(new pelicula(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
            ));
        }
        
        return list;
    }
    
    @Override
    public String add_pelicula(String nombre, String categoria, String idioma, String img) throws ClassNotFoundException, SQLException
    {
        Connection cn = conectar.con();
        CallableStatement cs = null;
        String res = null;
    
        cs= cn.prepareCall("{call insert_peli (?,?,?,?)}");
        
        cs.setString(1, nombre);
        cs.setString(2, categoria);
        cs.setString(3, idioma);
        cs.setString(4, img);
        
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) 
        {
            res = rs.getString(1).toString();
        }
        
        return res;
    }
    
    @Override
    public String add_presentacion(int id_pelicula, String hora, String sala) throws ClassNotFoundException, SQLException
    {
        Connection cn = conectar.con();
        CallableStatement cs = null;
        String res = null;
    
        cs= cn.prepareCall("{call insert_presentacion (?,?,?)}");
        
        cs.setInt(1, id_pelicula);
        cs.setString(2, hora);
        cs.setString(3, sala);
        
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) 
        {
            res = rs.getString(1).toString();
        }
        
        return res;
    }
    
    @Override
    public String compra_taquilla(String id_admin, int id_presentacion, int aciento, String cedula) throws ClassNotFoundException, SQLException
    {
        String res = null;
        
        Connection cn = conectar.con();
        
        CallableStatement cs = null;
        cs= cn.prepareCall("{call compra_taquilla (?,?,?,?)}");
        cs.setString(1, id_admin);
        cs.setInt(2, id_presentacion);
        cs.setInt(3, aciento);
        cs.setString(4, cedula);
        
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) 
        {
            res = rs.getString(1).toString();
        }
        
        return res;
    }
    
    @Override
    public String pago(int num_factura, int num_tarjeta, String fecha, String num_seguridad) throws ClassNotFoundException, SQLException
    {
        String res = null;
        
        Connection cn = conectar.con();
        
        CallableStatement cs = null;
        cs= cn.prepareCall("{call compra_taquilla (?,?,?,?)}");
        cs.setInt(1, num_factura);
        cs.setInt(2, num_tarjeta);
        cs.setString(3, fecha);
        cs.setString(4, num_seguridad);
        
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) 
        {
            res = rs.getString(1).toString();
        }
        
        return res;
    }
      
}
