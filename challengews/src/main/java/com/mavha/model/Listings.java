package com.mavha.model;

import java.math.BigDecimal;
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
 * Model listings
 * @author cande.bertoldi@gmail.com
 */
@Entity
@Table(name = "listings")
public class Listings {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true)
    private int id;
    
    //Define el nombre de la publicacion
    @Column(name="name", unique=false, nullable = false)
    private String name;
    
    //Define el slug de la publicacion
    @Column(name="slug", unique=true, nullable = false)
    private String slug;
    
    //Define la descripcion de la publicacion
    @Column(name="description", unique=true, nullable = false)
    private String description;
    
    //Define la cantidad de maxima adultos
    @Column(name="adults", unique=false, nullable = false)
    private int adults;

    //Define la cantidad de maxima niños
    @Column(name="children", unique=false, nullable = false)
    private int children;
    
    //Define si se permiten mascotas
    @Column(name="is_pets_allowed", unique=false, nullable = false)
    private boolean isPetsAllowed;
    
    //Define el precio
    @Column(name="base_price", unique=false, nullable = false)
    private BigDecimal basePrice;
    
    //Define el costo de servicio de limpieza
    @Column(name="cleaning_price", unique=false, nullable = false)
    private BigDecimal cleaningFee;
    
    //Define la url de la imagen
    @Column(name="image_url", unique=false, nullable = false)
    private String imageUrl;
    
    //Define el descuento por semana
    @Column(name="weekly_discount", unique=false, nullable = false)
    private BigDecimal weeklyDiscount;
    
    //Define el descuento por mes
    @Column(name="monthly_discount", unique=false, nullable = false)
    private BigDecimal monthlyDiscount;
    
    //Define el creador de la publicacion
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "owner_id", nullable = false)
    private Users owner;
    
    //Constructor
    public Listings() {
    }
    
    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public boolean isIsPetsAllowed() {
        return isPetsAllowed;
    }

    public void setIsPetsAllowed(boolean isPetsAllowed) {
        this.isPetsAllowed = isPetsAllowed;
    }

    public BigDecimal getCleaningFee() {
        return cleaningFee;
    }

    public void setCleaningFee(BigDecimal cleaningFee) {
        this.cleaningFee = cleaningFee;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getWeeklyDiscount() {
        return weeklyDiscount;
    }

    public void setWeeklyDiscount(BigDecimal weeklyDiscount) {
        this.weeklyDiscount = weeklyDiscount;
    }

    public BigDecimal getMonthlyDiscount() {
        return monthlyDiscount;
    }

    public void setMonthlyDiscount(BigDecimal monthlyDiscount) {
        this.monthlyDiscount = monthlyDiscount;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

}
