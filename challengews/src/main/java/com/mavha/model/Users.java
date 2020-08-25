package com.mavha.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model users
 * @author cande.bertoldi@gmail.com
 */
@Entity
@Table(name = "users")
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true)
    private int id;
    
    //Define el nombre del usuario
    @Column(name="name", unique=false, nullable = false)
    private String name;
    
    //Define el email del usuario
    @Column(name="email", unique=true, nullable = false)
    private String email;
    
    //Define la contraseña del usuario
    @Column(name="password", unique=false, nullable = false)
    private String password;

    //constructor
    public Users() {
    }
    
    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {    
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
