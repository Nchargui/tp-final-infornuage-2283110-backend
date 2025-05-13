package com.kriNad.backend.model.personne;

import jakarta.persistence.Entity;

@Entity
public class Admin extends Personne {
    private Boolean adminAcces = true;


    public Admin() {

    }

    public Admin(Long idUser, String fname, String lname, String email) {
        super(idUser, fname, lname, email);
    }



    @Override
    public Boolean getAdminAcces() {
        return adminAcces;
    }

    @Override
    public void setAdminAcces(Boolean adminAcces) {
        this.adminAcces = adminAcces;
    }
}
