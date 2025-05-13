package com.kriNad.backend.model.property;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Property {

    @Column(columnDefinition = "TEXT")
    private String description;
    private String categorie;
    private Double price;
    private String address;
    private Long nbRooms;
    private Long nbBathrooms;
    private Long nbParkingSpace;
    private Long nbGarages;
    private Double area;
    private Long ConstructionYear;
    private String city;
    private Boolean isAccepted;
    private Boolean isAvailable;





    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public Long getNbRooms() {
        return nbRooms;
    }

    public Long getNbBathrooms() {
        return nbBathrooms;
    }

    public Long getNbParkingSpace() {
        return nbParkingSpace;
    }

    public Long getNbGarages() {
        return nbGarages;
    }

    public Double getArea() {
        return area;
    }

    public Long getConstructionYear() {
        return ConstructionYear;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNbRooms(Long nbChambre) {
        this.nbRooms = nbChambre;
    }

    public void setNbBathrooms(Long nbSalleBain) {
        this.nbBathrooms = nbSalleBain;
    }

    public void setNbParkingSpace(Long nbstationnement) {
        this.nbParkingSpace = nbstationnement;
    }

    public void setNbGarages(Long nbGarage) {
        this.nbGarages = nbGarage;
    }

    public void setArea(Double superficie) {
        this.area = superficie;
    }

    public void setConstructionYear(Long anneeConstruction) {
        this.ConstructionYear = anneeConstruction;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
}



