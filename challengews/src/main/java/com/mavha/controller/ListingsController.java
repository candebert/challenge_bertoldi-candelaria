package com.mavha.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mavha.DTO.CheckOutDTO;
import com.mavha.model.Listings;
import com.mavha.service.ListingsService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
 * Class ListingsController: Define los endpoints de listings
 * @author cande.bertoldi@gmail.com
 */
@RestController
public class ListingsController {
    
     //Define la URL
    private final String URL = "/api/listings";

    //Define la instancia del servicio
    @Autowired
    ListingsService listingsService;
    
    //EndPoint para obtener por id
    @GetMapping(value = URL + "/show/{id}")
    @ResponseBody
    public Listings showById(@PathVariable int id) {
        return listingsService.showById(id);
    }
    
    //EndPoint para obtener por id de creador
    @GetMapping(value = URL + "/owner/{idUser}")
    @ResponseBody
    public List<Listings> listByOwner(@PathVariable int idUser) {
        return listingsService.listByUser(idUser);
    }
    
    //EndPoint para obtener todos los Listings
    @GetMapping(value = URL)
    @ResponseBody
    public List<Listings> listAll() {
        return listingsService.listAll();
    }
    
    //Endpoint para agregar un Listings
    @PostMapping(value = URL)
    public ResponseEntity<String> create(@RequestBody Listings l) {
        try {
            Listings l1 = listingsService.create(l);
            return new ResponseEntity<>("Agregado exitosamente. ID: " + l1.getId(),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Endpoint para eliminar un Listings
    @DeleteMapping(value = URL + "/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            listingsService.delete(id);
            return new ResponseEntity<>("Eliminado exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Endpoint para realizar el chaekout
    @GetMapping(value = URL + "/{idListing}/{dateStart}/{dateEnd}/checkout")
    @ResponseBody
    public ResponseEntity<?> checkout(@PathVariable int idListing, 
            @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStart,
            @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEnd) {
        CheckOutDTO c = listingsService.checkout(idListing, dateStart, dateEnd);
        return ResponseEntity.ok(c);
    }
}
