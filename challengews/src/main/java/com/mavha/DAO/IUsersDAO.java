package com.mavha.DAO;

import com.mavha.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz IUsersDAO: Define la conexion con la base de datos 
 * @author cande.bertoldi@gmail.com
 */
@Repository
public interface IUsersDAO extends JpaRepository<Users, Integer> {
    
}
