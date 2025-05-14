package com.kriNad.backend.controller;

import com.kriNad.backend.DTO.PropertySaleRequest;
import com.kriNad.backend.model.personne.Customer;
import com.kriNad.backend.model.property.ImagePropertySale;
import com.kriNad.backend.model.property.PropertySale;
import com.kriNad.backend.repositories.CustomerRepository;
import com.kriNad.backend.repositories.ImagePropertySaleRepository;
import com.kriNad.backend.repositories.PropertySaleRepository;
import com.kriNad.backend.service.PropertySaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Kristina et Nadine
@RestController
@RequestMapping("/PropertySale")
@CrossOrigin("*")
public class PropertySaleController {

    @Autowired
    private PropertySaleService propertySaleService;

    @Autowired
    private PropertySaleRepository propertySaleRepository;

    @Autowired
    private ImagePropertySaleRepository imagePropertySaleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    //????????????????????
    @GetMapping("/getAllProperty")
    public List<PropertySale> getAll() {
        return propertySaleService.getAll();
    }

    @GetMapping("/{id}")
    public PropertySale getById(@PathVariable Long id) {
        return propertySaleService.getById(id).orElseThrow(() -> new RuntimeException("PropertySale not found"));
    }

    @GetMapping("/customer/{customerId}")
    public List<PropertySale> getPropertySalesByCustomer(@PathVariable Long customerId) {
        return propertySaleRepository.getPropertyByCustomer(customerId);
    }

    @GetMapping("/agent/{id}")
    public List<PropertySale> getPropertyByAgent(@PathVariable Long id) {
        return propertySaleService.getPropertyByAgent(id);
    }

    @GetMapping("/filtre")
    public List<PropertySale> findPropertyFilters(
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
        return propertySaleService.findByFilters(categorie, minPrice, maxPrice, nbRooms, nbBathrooms, nbParking, nbGarages, minArea, maxArea, minYear, maxYear, city);
    }

    @PostMapping
    public PropertySale createPropertySale(@RequestBody PropertySaleRequest request) {
        if (request.getCustomerId() == null) {
            throw new RuntimeException("Customer ID is required!");
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        PropertySale property = new PropertySale();
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
        property.setIsAvailable(true);
        property.setTypeProperty("sale");
        property.setCustomer(customer);

        PropertySale savedProperty = propertySaleService.save(property);

        for (String imageLink : request.getImages()) {
            ImagePropertySale image = new ImagePropertySale();
            image.setImageLink(imageLink);
            image.setPropertySale(savedProperty);
            imagePropertySaleRepository.save(image);
        }

        return savedProperty;
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptSubmission(@PathVariable Long id) {
        propertySaleService.acceptSubmission(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/refuse/{id}")
    public ResponseEntity<?> refuseSubmission(@PathVariable Long id) {
        propertySaleService.refuseSubmission(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateOwner{id}")
    public ResponseEntity<?> assignAgent(@PathVariable Long id, @RequestParam Long idUser) {
        propertySaleService.assignAgent(id, idUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pending")
    public List<PropertySale> getPendingSales() {
        return propertySaleRepository.findUnassignedPendingSales();
    }
}
