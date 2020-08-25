package com.mavha.DAO;

import com.mavha.model.Listings;
import com.mavha.model.SpecialPrice;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interfaz ISpecialPriceDAO: Define la conexion con la base de datos 
 * @author cande.bertoldi@gmail.com
 */
@Repository
public interface ISpecialPriceDAO extends JpaRepository<SpecialPrice, Integer> {
    
    //Obtiene los precios especiales por Listings
    public List<SpecialPrice> findByListings(Listings listings); 
    
    //Obtiene un listado de precios especiales por listings y fecha
    @Query(value = "SELECT * FROM special_prices WHERE listing_id = :idListing AND "
            + "date BETWEEN :dateStart AND :dateEnd" ,nativeQuery = true)
    public List<SpecialPrice> listByListingsAndDate(@Param("idListing") int idListing,
            @Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd);
    
}
