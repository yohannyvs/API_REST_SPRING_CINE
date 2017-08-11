
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
    public ResponseEntity<List<pelicula>> peliculas() throws SQLException, ClassNotFoundException {
        
        List<pelicula> p = psi.getpeliculas();
        
        if(p.isEmpty()){
            return new ResponseEntity<List<pelicula>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<pelicula>>(p, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/add_pelicula/", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody pelicula p) throws ClassNotFoundException, SQLException
    {
    
        psi.add_pelicula(p.getNombre(), p.getCategoria(), p.getIdioma(), p.getImg());
        
        return ResponseEntity.ok(p);
    }
    
    @RequestMapping(value = "/add_presentacion/", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody presentacion pr) throws ClassNotFoundException, SQLException
    {
    
        psi.add_presentacion(pr.getId_pelicula(), pr.getHora(), pr.getSala());
        
        return ResponseEntity.ok(pr);
    }
    
}
