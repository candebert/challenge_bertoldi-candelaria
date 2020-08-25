package com.mavha.service;

import com.mavha.DAO.IListingsDAO;
import com.mavha.DAO.ISpecialPriceDAO;
import com.mavha.model.SpecialPrice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * class SpecialPriceService
 * @author cande.bertoldi@gmail.com
 */
@Service
public class SpecialPriceService {
    
    //Instancia del DAO
    @Autowired
    ISpecialPriceDAO specialPriceDAO;
    
    //Instancia del DAO de Listings
    @Autowired
    IListingsDAO listingsDAO;

    //Obtiene un usuario por id
    public SpecialPrice showById(int id) {
        return specialPriceDAO.findById(id).get();
    }
    
    //Obtiene el listado de todos los SpecialPrice por Listings
    public List<SpecialPrice> listByListings(int idListings) {
        return specialPriceDAO.findByListings(listingsDAO.findById(idListings).get());
    }
    
    //Obtiene el listado de todos los SpecialPrice
    public List<SpecialPrice> listAll() {
        return specialPriceDAO.findAll();
    }
    
    //Agrega un SpecialPrice
    @Transactional(rollbackFor = Exception.class)
    public SpecialPrice create(SpecialPrice u) {
        return specialPriceDAO.saveAndFlush(u);
    } 
    
    //Elimina un SpecialPrice
    @Transactional(rollbackFor = Exception.class)
    public void delete(int id) {
        specialPriceDAO.deleteById(id);
    } 
}
