package com.kriNad.backend.controller;

import com.kriNad.backend.model.personne.Customer;
import com.kriNad.backend.model.property.Favorite;
import com.kriNad.backend.model.property.PropertyRent;
import com.kriNad.backend.model.property.PropertySale;
import com.kriNad.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Kristina
@RestController
@RequestMapping("/Favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PropertySaleRepository propertySaleRepository;

    @Autowired
    private PropertyRentRepository propertyRentRepository;

    @PostMapping("/addFavorite")
    public Favorite addFavorite(@RequestParam Long customerId, @RequestParam Long propertyId, @RequestParam String type) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Favorite favorite = new Favorite();
        favorite.setCustomer(customer);

        if ("sale".equalsIgnoreCase(type)) {
            PropertySale propertySale = propertySaleRepository.findById(propertyId)
                    .orElseThrow(() -> new RuntimeException("PropertySale not found"));
            favorite.setPropertySale(propertySale);
        } else if ("rent".equalsIgnoreCase(type)) {
            PropertyRent propertyRent = propertyRentRepository.findById(propertyId)
                    .orElseThrow(() -> new RuntimeException("PropertyRent not found"));
            favorite.setPropertyRent(propertyRent);
        } else {
            throw new RuntimeException("Invalid property type. Must be 'sale' or 'rent'.");
        }

        return favoriteRepository.save(favorite);
    }

    @GetMapping("/{customerId}")
    public List<Favorite> getFavoritesByCustomer(@PathVariable Long customerId) {
        return favoriteRepository.findAll()
                .stream()
                .filter(fav -> fav.getCustomer() != null && fav.getCustomer().getId().equals(customerId))
                .toList();
    }

    @DeleteMapping("/{favoriteId}")
    public void deleteFavorite(@PathVariable Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }
}
