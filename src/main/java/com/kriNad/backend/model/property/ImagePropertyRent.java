package com.kriNad.backend.model.property;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ImagePropertyRent extends ImageProperty{

    public void setPropertyRent(PropertyRent propertyRent) {
        this.propertyRent = propertyRent;
    }


    @ManyToOne
    @JsonIgnore
    private PropertyRent propertyRent;

    public PropertyRent getPropertyRent() {
        return propertyRent;
    }
}
