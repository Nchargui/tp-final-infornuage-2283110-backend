package com.kriNad.backend.controller;

import com.kriNad.backend.DTO.PropertyRentRequest;
import com.kriNad.backend.model.personne.Customer;
import com.kriNad.backend.model.property.ImagePropertyRent;
import com.kriNad.backend.model.property.PropertyRent;
import com.kriNad.backend.repositories.CustomerRepository;
import com.kriNad.backend.repositories.ImagePropertyRentRepository;
import com.kriNad.backend.repositories.PropertyRentRepository;
import com.kriNad.backend.service.PropertyRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Nadine & Kristina
@RestController
@RequestMapping("/api/PropertyRent")
@CrossOrigin
public class PropertyRentController {

    @Autowired
    private PropertyRentService propertyRentService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ImagePropertyRentRepository imagePropertyRentRepository;

    @Autowired
    private PropertyRentRepository propertyRentRepository;

    //?????????????
    @GetMapping("/{id}")
    public PropertyRent getById(@PathVariable Long id) {
        return propertyRentService.getById(id).orElseThrow();
    }

    @GetMapping("/agent/{id}")
    public List<PropertyRent> getPropertyByAgent(@PathVariable Long id){
        return propertyRentService.getPropertyByAgent(id);
    }

    @GetMapping("/filtre")
    public List<PropertyRent> findPropertyFilters(
            @RequestParam(required = false) String categorie,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) Long nbRooms,
            @RequestParam(required = false) Long nbBathrooms,
            @RequestParam(required = false) Long nbParking,
            @RequestParam(required = false) Long nbGarages,
            @RequestParam(required = false) Long minArea,
            @RequestParam(required = false) Long maxArea,
            @RequestParam(required = false) Long minYear,
            @RequestParam(required = false) Long maxYear,
            @RequestParam(required = false) String city) {
        return propertyRentService.findByFilters(categorie, minPrice, maxPrice, nbRooms, nbBathrooms, nbParking, nbGarages, minArea, maxArea, minYear, maxYear, city);
    }

    @PostMapping
    public PropertyRent createPropertyRent(@RequestBody PropertyRentRequest request) {
        if (request.getCustomerId() == null) {
            throw new RuntimeException("Customer ID is required!");
        }

        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow();
        PropertyRent property = new PropertyRent();
        property.setDescription(request.getDescription());
        property.setCategorie(request.getCategorie());
        property.setPrice(request.getPrice());
        property.setAddress(request.getAddress());
        property.setNbRooms(request.getNbRooms());
        property.setNbBathrooms(request.getNbBathrooms());
        property.setNbParkingSpace(request.getNbParkingSpace());
        property.setNbGarages(request.getNbGarages());
        property.setArea(request.getArea());
        property.setConstructionYear(request.getConstructionYear());
        property.setCity(request.getCity());
        property.setIsAccepted(null);
        property.setCustomer(customer);
        property.setIsAvailable(true);
        property.setTypeProperty("rent");
        property.setMaxOccupants(request.getMaxOccupants());

        PropertyRent savedProperty = propertyRentService.save(property);

        for (String imageLink : request.getImages()) {
            ImagePropertyRent image = new ImagePropertyRent();
            image.setImageLink(imageLink);
            image.setPropertyRent(savedProperty);
            imagePropertyRentRepository.save(image);
        }

        return savedProperty;
    }

    @GetMapping("/customer/{customerId}")
    public List<PropertyRent> getPropertyRentsByCustomer(@PathVariable Long customerId) {
        return propertyRentRepository.getPropertyByCustomer(customerId);
    }

    @GetMapping("/pending")
    public List<PropertyRent> getPendingRentals() {
        return propertyRentService.getPendingRentals();
    }

    @PutMapping("/accept/{id}")
    public void accept(@PathVariable Long id) {
        propertyRentService.acceptSubmission(id);
    }

    @PutMapping("/refuse/{id}")
    public void refuse(@PathVariable Long id) {
        propertyRentService.refuseSubmission(id);
    }

    @PutMapping("/updateOwner{id}")
    public void assignAgent(@PathVariable Long id, @RequestParam Long idUser) {
        propertyRentService.assignAgent(id, idUser);
    }
}
