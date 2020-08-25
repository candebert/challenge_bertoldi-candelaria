package com.mavha.DAO;

import com.mavha.model.Listings;
import com.mavha.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz IListingsDAO: Define la conexion con la base de datos 
 * @author cande.bertoldi@gmail.com
 */
@Repository
public interface IListingsDAO extends JpaRepository<Listings, Integer> {
    
    //Obtiene las publicaciones por owner
    public List<Listings> findByOwner(Users Owner); 
    
}
