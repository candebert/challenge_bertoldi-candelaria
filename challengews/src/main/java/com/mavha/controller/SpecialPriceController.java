package com.mavha.controller;

import com.mavha.model.SpecialPrice;
import com.mavha.service.SpecialPriceService;
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
 * Class SpecialPriceController: Define los endpoints de special price
 * @author cande.bertoldi@gmail.com
 */
@RestController
public class SpecialPriceController {
    
     //Define la URL
    private final String URL = "/api/specialprice";

    //Define la instancia del servicio
    @Autowired
    SpecialPriceService specialPriceService;
    
    //EndPoint para obtener por id
    @GetMapping(value = URL + "/show/{id}")
    @ResponseBody
    public SpecialPrice showById(@PathVariable int id) {
        return specialPriceService.showById(id);
    }
    
    //EndPoint para obtener por id de listings
    @GetMapping(value = URL + "/listing/{idLis}")
    @ResponseBody
    public List<SpecialPrice> listByListing(@PathVariable int idLis) {
        return specialPriceService.listByListings(idLis);
    }
    
    //EndPoint para obtener todos los SpecialPrice
    @GetMapping(value = URL)
    @ResponseBody
    public List<SpecialPrice> listAll() {
        return specialPriceService.listAll();
    }
    
    //Endpoint para agregar un SpecialPrice
    @PostMapping(value = URL)
    public ResponseEntity<String> create(@RequestBody SpecialPrice l) {
        try {
            SpecialPrice l1 = specialPriceService.create(l);
            return new ResponseEntity<>("Agregado exitosamente. ID: " + l1.getId(),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Endpoint para eliminar un SpecialPrice
    @DeleteMapping(value = URL + "/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            specialPriceService.delete(id);
            return new ResponseEntity<>("Eliminado exitosamente.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
