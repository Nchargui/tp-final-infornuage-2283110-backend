package com.kriNad.backend.model.Request;

import com.kriNad.backend.model.personne.Agent;
import com.kriNad.backend.model.property.PropertyRent;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class RequestRent extends Request {


    @SequenceGenerator(sequenceName = "rent_id_seq", allocationSize = 1, name="unique_seq")


    @ManyToOne
    private PropertyRent propertyRent;

    public RequestRent() {

    }

    public RequestRent(String description, String statusDemande, String typeDemande, Agent agent) {
        super(description, statusDemande, typeDemande, agent);
    }




    public void setPropertyRent(PropertyRent propertyRent) {
        this.propertyRent = propertyRent;
    }

    public PropertyRent getPropertyRent() {
        return propertyRent;
    }
}
