package com.mavha.DTO;

import java.math.BigDecimal;

/**
 * DTO de checout: Creado para retornar los datos de la reserva
 * Atributos: NigthsCount, nightCost, discount, cleaningFee, total
 * @author cerno
 */
public class CheckOutDTO {
    
    //Cantidad de noches
    private int nightsCount;
    
    //Costo por noche
    private BigDecimal nightCost;
    
    //Descuento total
    private BigDecimal discount;
    
    //Costo total
    private BigDecimal nightsCost;
    
    //costo por limpieza
    private BigDecimal cleaningFee;
    
    //Costo total
    private BigDecimal total;
    
    //Constructor
    public CheckOutDTO() {
    }
    
    //Getters & setters
    public int getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(int nightsCount) {
        this.nightsCount = nightsCount;
    }

    public BigDecimal getNightCost() {
        return nightCost;
    }

    public void setNightCost(BigDecimal nightCost) {
        this.nightCost = nightCost;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalNights() {
        return nightsCost;
    }

    public void setTotalNights(BigDecimal totalNights) {
        this.nightsCost = totalNights;
    }

    public BigDecimal getCleaningFee() {
        return cleaningFee;
    }

    public void setCleaningFee(BigDecimal cleaningFee) {
        this.cleaningFee = cleaningFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
}
