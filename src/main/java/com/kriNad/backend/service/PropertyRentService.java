package com.kriNad.backend.service;

import com.kriNad.backend.model.personne.Agent;
import com.kriNad.backend.model.property.PropertyRent;
import com.kriNad.backend.repositories.AgentRepository;
import com.kriNad.backend.repositories.PropertyRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// kristina et nadine
@Service
public class PropertyRentService {

    @Autowired
    private PropertyRentRepository propertyRentRepository;

    @Autowired
    private AgentRepository agentRepository;

    public Optional<PropertyRent> getById(Long id) {
        return propertyRentRepository.findById(id);
    }

    public List<PropertyRent> getPropertyByAgent(Long idUser) {
        return propertyRentRepository.getPropertyByAgent(idUser);
    }

    public List<PropertyRent> findByFilters(String categorie, Long minPrice, Long maxPrice, Long nbRooms, Long nbBathrooms, Long nbParking, Long nbGarages, Long minArea, Long maxArea, Long minYear, Long maxYear, String city) {
        return propertyRentRepository.findPropertyFilters(categorie, minPrice, maxPrice, nbRooms, nbBathrooms, nbParking, nbGarages, minArea, maxArea, minYear, maxYear, city);
    }

    public PropertyRent save(PropertyRent propertyRent) {
        return propertyRentRepository.save(propertyRent);
    }

    public void acceptSubmission(Long id) {
        PropertyRent property = propertyRentRepository.findById(id).orElseThrow();
        property.setIsAccepted(true);
        propertyRentRepository.save(property);
    }

    public void refuseSubmission(Long id) {
        PropertyRent property = propertyRentRepository.findById(id).orElseThrow();
        property.setIsAccepted(false);
        propertyRentRepository.save(property);
    }

    public void assignAgent(Long propertyId, Long agentId) {
        PropertyRent property = propertyRentRepository.findById(propertyId).orElseThrow();
        Agent agent = agentRepository.findById(agentId).orElseThrow();
        property.setAgent(agent);
        propertyRentRepository.save(property);
    }

    public List<PropertyRent> getPendingRentals() {
        return propertyRentRepository.findUnassignedPendingRents();
    }
}
