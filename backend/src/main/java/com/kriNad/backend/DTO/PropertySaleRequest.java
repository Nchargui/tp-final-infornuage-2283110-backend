package com.kriNad.backend.DTO;

import java.util.List;

public class PropertySaleRequest {
    private String description;
    private String categorie;
    private Double price;
    private String address;
    private Long nbRooms;
    private Long nbBathrooms;
    private Long nbParkingSpace;
    private Long nbGarages;
    private Double area;
    private Long constructionYear;
    private String city;
    private List<String> images;
    private Long customerId;

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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
        return constructionYear;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getImages() {
        return images;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
