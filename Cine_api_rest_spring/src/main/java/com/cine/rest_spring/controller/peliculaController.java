
package com.cine.rest_spring.controller;

import com.cine.rest_spring.model.*;
import com.cine.rest_spring.service.cine_service;
import com.cine.rest_spring.service.*;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class peliculaController 
{
    @Autowired
    peliculaServiceInterface psi;
    
    @RequestMapping(value = "/pelicula/", method = RequestMethod.GET)
    public ResponseEntity<List<pelicula>> peliculas() throws SQLException, ClassNotFoundException 
    {
        
        List<pelicula> p = psi.getpeliculas();
        
        if(p.isEmpty()){
            return new ResponseEntity<List<pelicula>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<pelicula>>(p, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pelicula_categoria", method = RequestMethod.GET)
    public ResponseEntity<List<pelicula>> peliculas_categoria(@RequestParam( value="categoria")String categoria) throws SQLException, ClassNotFoundException 
    {
        
        List<pelicula> p = psi.getpeliculascategoria(categoria);
        
        if(p.isEmpty()){
            return new ResponseEntity<List<pelicula>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<pelicula>>(p, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pelicula_idioma", method = RequestMethod.GET)
    public ResponseEntity<List<pelicula>> peliculas_idioma(@RequestParam( value="idioma")String idioma) throws SQLException, ClassNotFoundException 
    {
        
        if(idioma.equals("Espanol"))
        {
            idioma = "Español";
        }
        
        List<pelicula> p = psi.getpeliculasidioma(idioma);
        
        if(p.isEmpty()){
            return new ResponseEntity<List<pelicula>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<pelicula>>(p, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pelicula_nombre", method = RequestMethod.GET)
    public ResponseEntity<List<pelicula>> peliculas_nombre(@RequestParam( value="nombre")String nombre) throws SQLException, ClassNotFoundException 
    {
        
        List<pelicula> p = psi.getpeliculasnombre(nombre);
        
        if(p.isEmpty()){
            return new ResponseEntity<List<pelicula>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<pelicula>>(p, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/add_pelicula", method = RequestMethod.GET)
    public ResponseEntity<String> add_pelicula(@RequestParam( value="nombre")String nombre, @RequestParam(value="categoria") String categoria,
                                      @RequestParam( value="idioma") String idioma, @RequestParam( value="img") String img)
            throws ClassNotFoundException, SQLException
    {
        
        String res = psi.add_pelicula(nombre, categoria, idioma, img);
        
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/add_presentacion", method = RequestMethod.GET)
    public ResponseEntity<String> add_presentacion(@RequestParam( value="pelicula")int id_peli, @RequestParam(value="hora") String hora,
                                      @RequestParam( value="sala") String sala)
            throws ClassNotFoundException, SQLException
    {
        String res = psi.add_presentacion(id_peli, hora, sala);
        
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/compra", method = RequestMethod.GET)
    public ResponseEntity<String> compra_tiquete(@RequestParam( value="admin")String admin, @RequestParam(value="id_present") int id_present,
                                       @RequestParam( value="aciento") int aciento, @RequestParam( value="cedula") String cedula)
            throws ClassNotFoundException, SQLException 
    {      
        String res = null;
        
        res = psi.compra_taquilla(admin, id_present, aciento, cedula);
        
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/pago", method = RequestMethod.GET)
    public ResponseEntity<String> pago(@RequestParam( value="num_fact")int num_fact, @RequestParam(value="num_tar") int num_tar,
                                       @RequestParam( value="fecha") String fecha, @RequestParam( value="num_seg") String num_seg)
            throws ClassNotFoundException, SQLException 
    {      
        String res = null;
        
        res = psi.pago(num_fact, num_tar, fecha, num_seg);
        
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/acientos", method = RequestMethod.GET)
    public ResponseEntity<List<String>> acientos(@RequestParam( value="presentacion")int presentacion)
            throws ClassNotFoundException, SQLException 
    {      
        List<String> res = psi.getacientos_ocupado(presentacion);
        
        if(res.isEmpty()){
            return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<String>>(res, HttpStatus.OK);
    }
    
}
