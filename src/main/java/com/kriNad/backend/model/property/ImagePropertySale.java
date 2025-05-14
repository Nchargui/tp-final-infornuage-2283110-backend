package com.kriNad.backend.model.property;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ImagePropertySale extends ImageProperty{

    @ManyToOne
    @JsonIgnore
    private PropertySale propertySale;

    public PropertySale getPropertySale() {
        return propertySale;
    }

    public void setPropertySale(PropertySale propertySale) {
        this.propertySale = propertySale;
    }
}
