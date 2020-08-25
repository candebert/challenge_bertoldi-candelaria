package com.mavha.service;

import com.mavha.DAO.IListingsDAO;
import com.mavha.DAO.ISpecialPriceDAO;
import com.mavha.DAO.IUsersDAO;
import com.mavha.DTO.CheckOutDTO;
import com.mavha.model.Listings;
import com.mavha.model.SpecialPrice;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * class ListingsService
 * @author cande.bertoldi@gmail.com
 */
@Service
public class ListingsService {
    
    //Instancia del DAO
    @Autowired
    IListingsDAO listingsDAO;
    
    //Instancia de UsersDAO
    @Autowired
    IUsersDAO usersDAO;

    //Instancia de SpecialPriceDAO
    @Autowired
    ISpecialPriceDAO specialPriceDAO;
    
    //Obtiene un usuario por id
    public Listings showById(int id) {
        return listingsDAO.findById(id).get();
    }
    
    //Obtiene el listado de todos los Listings por creador
    public List<Listings> listByUser(int idOwner) {
        return listingsDAO.findByOwner(usersDAO.findById(idOwner).get());
    }
    
    //Obtiene el listado de todos los Listings
    public List<Listings> listAll() {
        return listingsDAO.findAll();
    }
    
    //Agrega un Listings
    @Transactional(rollbackFor = Exception.class)
    public Listings create(Listings u) {
        return listingsDAO.saveAndFlush(u);
    } 
    
    //Elimina un Listings
    @Transactional(rollbackFor = Exception.class)
    public void delete(int id) {
        List<SpecialPrice> sp = specialPriceDAO.findByListings(
                listingsDAO.findById(id).get());
        if(!sp.isEmpty()) {
            sp.forEach((p) -> specialPriceDAO.deleteById(p.getId()));
        }
        listingsDAO.deleteById(id);
    } 
    
    //Calcula el costo de reservacion
    public CheckOutDTO checkout(int idListings, Date checkinDate, Date checkoutDate) {
        //Obtiene el anuncio por id
        Listings l = listingsDAO.findById(idListings).get();
        //Declara un dto para el envio de datos
        CheckOutDTO checkout = new CheckOutDTO();
        checkout.setTotal(new BigDecimal(0));
        checkout.setDiscount(new BigDecimal(0));
        //Calcula la cantidad de dias
        int days = (int) ((checkoutDate.getTime()-checkinDate.getTime())/86400000);
        //Calcula el costo de limpieza por la cantidad de dias
        checkout.setCleaningFee(l.getCleaningFee().multiply(new BigDecimal(days)));
        checkout.setNightsCount(days);
        checkout.setNightCost(l.getBasePrice());
        //Obtiene la lista de precios especiales por id de anuncio y las fechas de estadia
        List<SpecialPrice> prices = specialPriceDAO.listByListingsAndDate(l.getId(),checkinDate, checkoutDate);
        /*Recorre la lista de precios 
        * resta un dia por cada lista de precio que haya
        * agrega al monto total el precio especial
        * Agrega la diferencia del precio base con el precio especial a descuento
        */
        for (SpecialPrice price : prices) {
            days--;
            checkout.setTotal(checkout.getTotal().add(price.getPrice()));
            checkout.setDiscount(checkout.getDiscount().add(l.getBasePrice().subtract(price.getPrice())));
        }
        //Calcula el costo de los dias restantes con el precio base
        checkout.setTotal(checkout.getTotal().add(l.getBasePrice().multiply(new BigDecimal(days))));
        /*Si pasa mas de una semana pero no un mes aplica descuento semanal
        * Si pasa mas de un mes se aplica descuento mensual
        */
        if (checkout.getNightsCount()>7 && checkout.getNightsCount()<30) {
            checkout.setTotal(checkout.getTotal().subtract(l.getWeeklyDiscount()));
            checkout.setDiscount(checkout.getDiscount().add(l.getWeeklyDiscount()));
        } else if(checkout.getNightsCount()>30) {
            checkout.setTotal(checkout.getTotal().subtract(l.getMonthlyDiscount()));
            checkout.setDiscount(checkout.getDiscount().add(l.getMonthlyDiscount()));
        }
        checkout.setTotalNights(checkout.getTotal());
        checkout.setTotal(checkout.getTotal().add(checkout.getCleaningFee()));
        return checkout;
    }
    
}
