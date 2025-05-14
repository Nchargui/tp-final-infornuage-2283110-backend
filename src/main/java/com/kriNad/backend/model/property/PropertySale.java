package com.kriNad.backend.model.property;

import com.kriNad.backend.model.personne.Agent;
import com.kriNad.backend.model.personne.Customer;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
public class PropertySale extends Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProperty;


    @ColumnDefault("'sale'")
    private String typeProperty;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Agent agent;


    @OneToMany(mappedBy = "propertySale", cascade = CascadeType.ALL)
    private List<ImagePropertySale> images;

    public Long getIdProperty() {
        return idProperty;
    }


    public void setIdProperty(Long idProperty) {
        this.idProperty = idProperty;
    }


    public String getTypeProperty() {
        return typeProperty;
    }

    public List<ImagePropertySale> getImages() {
        return images;
    }

    public void setImages(List<ImagePropertySale> images) {
        this.images = images;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public void setTypeProperty(String typeProperty) {
        this.typeProperty = typeProperty;
    }
}
