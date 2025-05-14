package com.kriNad.backend.service;

import com.kriNad.backend.model.personne.Agent;
import com.kriNad.backend.model.property.PropertySale;
import com.kriNad.backend.repositories.AgentRepository;
import com.kriNad.backend.repositories.PropertySaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// kristina et nadine
@Service
public class PropertySaleService {

    @Autowired
    private PropertySaleRepository propertySaleRepository;

    @Autowired
    private AgentRepository agentRepository;

    public PropertySaleService(PropertySaleRepository propertySaleRepository) {
        this.propertySaleRepository = propertySaleRepository;
    }

    public List<PropertySale> getAll() {
        return propertySaleRepository.findAll();
    }

    public Optional<PropertySale> getById(Long id) {
        return propertySaleRepository.findById(id);
    }

    public List<PropertySale> getPropertyByAgent(long idUser) {
        return propertySaleRepository.getPropertyByAgent(idUser);
    }

    public List<PropertySale> findByFilters(String categorie, Long minPrice, Long maxPrice, Long nbRooms, Long nbBathrooms, Long nbParking, Long nbGarages, Long minArea, Long maxArea, Long minYear, Long maxYear, String city) {
        return propertySaleRepository.findPropertyFilters(categorie, minPrice, maxPrice, nbRooms, nbBathrooms, nbParking, nbGarages, minArea, maxArea, minYear, maxYear, city);
    }

    public PropertySale save(PropertySale propertySale) {
        return propertySaleRepository.save(propertySale);
    }

    public void acceptSubmission(Long id) {
        PropertySale property = propertySaleRepository.findById(id).orElseThrow();
        property.setIsAccepted(true);
        propertySaleRepository.save(property);
    }

    public void refuseSubmission(Long id) {
        PropertySale property = propertySaleRepository.findById(id).orElseThrow();
        property.setIsAccepted(false);
        propertySaleRepository.save(property);
    }

    public void assignAgent(Long propertyId, Long agentId) {
        PropertySale property = propertySaleRepository.findById(propertyId).orElseThrow();
        Agent agent = agentRepository.findById(agentId).orElseThrow();
        property.setAgent(agent);
        propertySaleRepository.save(property);
    }
}
