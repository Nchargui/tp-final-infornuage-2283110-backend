package com.kriNad.backend.model.property;

import com.kriNad.backend.model.personne.Customer;
import jakarta.persistence.*;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFavorite;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private PropertySale propertySale;

    @ManyToOne
    private PropertyRent propertyRent;



    public Long getIdFavorite() {
        return idFavorite;
    }

    public void setIdFavorite(Long idFavorite) {
        this.idFavorite = idFavorite;
    }

    public PropertySale getPropertySale() {
        return propertySale;
    }

    public PropertyRent getPropertyRent() {
        return propertyRent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPropertySale(PropertySale propertySale) {
        this.propertySale = propertySale;
    }

    public void setPropertyRent(PropertyRent propertyRent) {
        this.propertyRent = propertyRent;
    }
}
