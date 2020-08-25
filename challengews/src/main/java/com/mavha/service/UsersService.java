package com.mavha.service;

import com.mavha.DAO.IListingsDAO;
import com.mavha.DAO.IUsersDAO;
import com.mavha.model.Listings;
import com.mavha.model.Users;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * class UsersService
 * @author cande.bertoldi@gmail.com
 */
@Service
public class UsersService {
    
    //Instancia del DAO
    @Autowired
    IUsersDAO usersDAO;
    
    //Instancia del DAO de listings
    @Autowired
    IListingsDAO listingsDAO;
    
    //Instancia del encriptador de pass
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    //Obtiene un usuario por id
    public Users showById(int id) {
        return usersDAO.findById(id).get();
    }
    
    //Obtiene el listado de todos los usuarios
    public List<Users> listAll() {
        return usersDAO.findAll();
    }
    
    //Agrega un usuario
    @Transactional(rollbackFor = Exception.class)
    public Users create(Users u) {
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        return usersDAO.saveAndFlush(u);
    } 
    
    //Elimina un usuario
    @Transactional(rollbackFor = Exception.class)
    public void delete(int id) {
        List<Listings> l = listingsDAO.findByOwner(
                usersDAO.findById(id).get());
        if(!l.isEmpty()) {
            l.forEach((p) -> listingsDAO.deleteById(p.getId()));
        }
        usersDAO.deleteById(id);
    } 
}
