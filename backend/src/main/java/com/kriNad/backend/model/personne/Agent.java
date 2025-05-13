package com.kriNad.backend.model.personne;

import jakarta.persistence.*;

@Entity
public class Agent extends Personne {
    private Boolean agentAcces = true;

    public Agent() {

    }

    public Agent(Long idUser, String fname, String lname, String email) {
        super(idUser, fname, lname, email);
    }



    @Override
    public Boolean getAgentAcces() {
        return agentAcces;
    }

    @Override
    public void setAgentAcces(Boolean agentAcces) {
        this.agentAcces = agentAcces;
    }
}
