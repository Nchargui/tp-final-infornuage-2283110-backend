package com.kriNad.backend.model.property;
import com.kriNad.backend.model.personne.Agent;
import com.kriNad.backend.model.personne.Customer;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class PropertyRent extends Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProperty;

    private Long maxOccupants;

    @ColumnDefault("'rent'")
    private String typeProperty;


    public void setIdProperty(Long idProperty) {
        this.idProperty = idProperty;
    }


    public String getTypeProperty() {
        return typeProperty;
    }

    public Long getIdProperty() {
        return idProperty;
    }




    public Long getMaxOccupants() {
        return maxOccupants;
    }

    public void setMaxOccupants(Long maxOccupants) {
        this.maxOccupants = maxOccupants;
    }

    public void setTypeProperty(String typeProperty) {
        this.typeProperty = typeProperty;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Occupant getOccupant() {
        return occupant;
    }

    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }

    @ManyToOne
    private Agent agent;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Occupant occupant;
}
