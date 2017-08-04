
package com.cine.rest_spring.controller;

import com.cine.rest_spring.service.cine_service;
import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController 
{
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<String> hola(@RequestParam( value="user")String user, @RequestParam(value="pass") String pass) throws ClassNotFoundException, SQLException 
    {
        cine_service cs = new cine_service();
        int valor = cs.login(user, pass);
        
        String res = valor+"";
        
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }
}
