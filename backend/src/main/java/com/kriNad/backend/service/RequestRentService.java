package com.kriNad.backend.service;

import com.kriNad.backend.exception.CustomerNotFoundException;
import com.kriNad.backend.exception.RequestAppliedRentException;
import com.kriNad.backend.exception.RequestNotFoundException;
import com.kriNad.backend.model.Request.RequestRent;
import com.kriNad.backend.model.personne.Customer;
import com.kriNad.backend.model.property.Occupant;
import com.kriNad.backend.model.property.PropertyRent;
import com.kriNad.backend.repositories.*;
import org.springframework.stereotype.Service;
import com.kriNad.backend.model.property.Property;

import java.util.Arrays;
import java.util.List;


// nadine
@Service
public class RequestRentService {

    private final PropertyRentService propertyRentService;
    List<String> requestStatus = Arrays.asList("accepted", "rejected", "pending");

    private final PropertyRentRepository propertyRentRepository;
    private final RequestRentRepository requestRentRepository;
    private final CustomerRepository customerRepository;
    private final OccupantRepository occupantRepository;

    public RequestRentService(RequestRentRepository requestRentRepository, PropertySaleRepository propertySaleRepository, CustomerRepository customerRepository, PropertyRentRepository propertyRentRepository, OccupantRepository occupantRepository, PropertyRentService propertyRentService) {
        this.requestRentRepository = requestRentRepository;
        this.customerRepository = customerRepository;
        this.propertyRentRepository = propertyRentRepository;
        this.occupantRepository = occupantRepository;
        this.propertyRentService = propertyRentService;
    }

    //////Get
    public RequestRent getRequestRentById(Long IdDemande) {
        return requestRentRepository.findById(IdDemande).orElseThrow(() -> new RequestNotFoundException());
    }

    public List<RequestRent> getAllRequestByCustomerId(Long customerId) {
        return requestRentRepository.getRequestRentByCustomer_Id(customerId);
    }

    public List<RequestRent> getAllRequestByAgentId(Long agentId) {
        return requestRentRepository.getRequestRentByAgent_Id(agentId);
    }
    public Customer getCustomerById(Long idUser) {
        return customerRepository.findById(idUser).orElseThrow(() -> new CustomerNotFoundException(idUser));
    }

    ////Verify
    public boolean isApplied(Long customerId, Long propertyRentId) {
        return requestRentRepository.existsByCustomer_IdAndPropertyRent_IdProperty(customerId, propertyRentId);

    }

    /////Create
    public RequestRent createRequest(RequestRent requestRent){
        if (isApplied(requestRent.getCustomer().getId(), requestRent.getPropertyRent().getIdProperty())){
            throw new RequestAppliedRentException(requestRent);
        }
        return requestRentRepository.save(requestRent);
    }

    ////////////////Update | Delete, Accept////////////////////////////////////
    public RequestRent updateRequest(Long IdDemande, String status) {

        return requestRentRepository.findById(IdDemande).map(requestRent -> {
            requestRent.setStatusDemande(status);
            return requestRentRepository.save(requestRent);

        }).orElseThrow(() -> new RequestNotFoundException());
    }

    public RequestRent acceptRequest(Long IdDemande) {
        return updateRequest(IdDemande, requestStatus.get(0));
    }

    public RequestRent rejectRequest(Long IdDemande) {
        return updateRequest(IdDemande, requestStatus.get(1));
    }

    /////////////////////////Update Occupants/////////////////////////////////////
    public PropertyRent updateOccupant(Long IdDemande, Long idUser){
        Customer customer = getCustomerById(idUser);
        RequestRent requestRent = requestRentRepository.findById(IdDemande).orElseThrow(() -> new RequestNotFoundException());

        PropertyRent propertyRent = requestRent.getPropertyRent();

        Occupant occupant = propertyRent.getOccupant();
        if (occupant == null){
            occupant = new Occupant();
            occupantRepository.save(occupant);
            propertyRent.setOccupant(occupant);
            propertyRentRepository.save(propertyRent);
        }

        customer.setOccupant(occupant);
        customerRepository.save(customer);

        return propertyRentRepository.save(propertyRent);
    }

    /////////////////////////Remove property from market/////////////////////////////////////
    public PropertyRent updateMaxOccupant(Long IdDemande, Long idUser){
        PropertyRent propertyRent = updateOccupant(IdDemande, idUser);
        Long countOccpants = occupantRepository.countOccupantByProperty(propertyRent.getIdProperty());
        System.out.println( "nbr of occupant : " + countOccpants);

        if (propertyRent.getMaxOccupants() != null && propertyRent.getMaxOccupants().equals(countOccpants)){
            propertyRent.setIsAvailable(false);
            propertyRentRepository.save(propertyRent);

        }

        return propertyRent;
    }

    /////////////////Delete//////////////////////////////////////
    public void deleteRequestWithId(Long IdDemande) {

        RequestRent requestRent = getRequestRentById(IdDemande);

        if (requestRent.getStatusDemande().equals(requestStatus.get(0))) {
            throw new RuntimeException("Cannot delete a request that has been accepted");
        } else {
            requestRentRepository.deleteById(IdDemande);
        }
    }
}
