package com.cine.rest_spring.controller;

import com.cine.rest_spring.model.prueba_model;
import com.cine.rest_spring.service.cine_service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class holamundo 
{
    public static List<prueba_model> p = new ArrayList<prueba_model>();
    
    @RequestMapping(value = "/prueba/", method = RequestMethod.GET)
    public ResponseEntity<List<prueba_model>> hola() {
        //String hola = "Hola Mundo Anime"
        
        if(p.isEmpty()){
            return new ResponseEntity<List<prueba_model>>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<List<prueba_model>>(p, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/hola/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> _hola( @PathVariable("id") int id ) {
        
        String holas[] = {"Hola","Mundo","Anime"};
        
        String hola = holas[id];
        
        return new ResponseEntity<String>(hola, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/hola", method = RequestMethod.GET)
    public ResponseEntity<String> hola(@RequestParam( value="name", defaultValue = "Mundo" ) String name) {
        
        String hola = "Hola " + name;
        
        return new ResponseEntity<String>(hola, HttpStatus.OK);
    }
    
    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> hola(@RequestParam( value="user")String user, @RequestParam(value="pass") String pass) throws ClassNotFoundException, SQLException 
    {
        cine_service cs = new cine_service();
        int valor = cs.login(user, pass);
        
        String res = valor+"";
        
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }*/
    
    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody prueba_model prueba)
    {
    
        p.add(
            new prueba_model(
                    prueba.getId(),
                    prueba.getNombre()
        ));
        
        return ResponseEntity.ok(p);
    }
    
}
