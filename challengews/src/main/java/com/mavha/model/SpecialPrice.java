package com.mavha.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model specialPrice
 * @author cande.bertoldi@gmail.com
 */
@Entity
@Table(name = "special_prices")
public class SpecialPrice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true)
    private int id;
    
    //Define el dia del precio especial
    @Column(name="date", unique=true, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    
    //Define el precio especial
    @Column(name="price", unique=false, nullable = false)
    private BigDecimal price;

    //Define el listing del precio especial
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "listing_id", nullable = false)
    private Listings listings;
    //constructor
    public SpecialPrice() {
    }
    
    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Listings getListings() {
        return listings;
    }

    public void setListings(Listings listings) {
        this.listings = listings;
    }

}
