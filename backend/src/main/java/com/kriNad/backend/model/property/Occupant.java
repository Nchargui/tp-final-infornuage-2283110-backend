package com.kriNad.backend.model.property;

import jakarta.persistence.*;

// plusieurs propriétés à louer pour plusieurs occupant
@Entity
public class Occupant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
