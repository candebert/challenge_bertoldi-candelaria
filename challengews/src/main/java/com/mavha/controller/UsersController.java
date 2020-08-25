package com.mavha.controller;

import com.mavha.model.Users;
import com.mavha.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class UsersController: Define los endpoints de users
 * @author cande.bertoldi@gmail.com
 */
@RestController
public class UsersController {
    
     //Define la URL
    private final String URL = "/api/users";

    //Define la instancia del servicio
    @Autowired
    UsersService usersService;
    
    //EndPoint para obtener por id
    @GetMapping(value = URL + "/show/{id}")
    @ResponseBody
    public Users showById(@PathVariable int id) {
        return usersService.showById(id);
    }
    
    //EndPoint para obtener todos los usuarios
    @GetMapping(value = URL)
    @ResponseBody
    public List<Users> listAll() {
        return usersService.listAll();
    }
    
    //Endpoint para agreggar un usuario
    @PostMapping(value = URL)
    public ResponseEntity<String> create(@RequestBody Users users) {
        try {
            Users u = usersService.create(users);
            return new ResponseEntity<>("Agregado exitosamente. ID: " + u.getId(),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Endpoint para eliminar un usuario
    @DeleteMapping(value = URL + "/{idUser}")
    public ResponseEntity<String> delete(@PathVariable int idUser) {
        try {
            usersService.delete(idUser);
            return new ResponseEntity<>("Eliminado exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
