package com.kriNad.backend.model.personne;

import com.kriNad.backend.model.property.Occupant;
import jakarta.persistence.*;

@Entity
public class Customer extends Personne {

    @ManyToOne
    private Occupant occupant;

    public Customer() {
    }


    public Customer(Long idUser, String fname, String lname, String email) {
        super(idUser, fname, lname, email);
    }


    public Occupant getOccupant() {
        return occupant;
    }



    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }
}
