package com.kriNad.backend.service;

import com.kriNad.backend.exception.CustomerNotFoundException;
import com.kriNad.backend.exception.PropertySaleNotFoundException;
import com.kriNad.backend.exception.RequestAppliedSaleException;
import com.kriNad.backend.exception.RequestNotFoundException;
import com.kriNad.backend.model.Request.RequestSale;
import com.kriNad.backend.model.personne.Customer;
import com.kriNad.backend.model.property.PropertySale;
import com.kriNad.backend.repositories.CustomerRepository;
import com.kriNad.backend.repositories.PropertySaleRepository;
import com.kriNad.backend.repositories.RequestSaleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


// nadine
@Service
public class RequestSaleService {

    List<String> requestStatus = Arrays.asList("accepted", "rejected", "pending");

    private final RequestSaleRepository requestSaleRepository;
    private final PropertySaleRepository propertySaleRepository;
    private final CustomerRepository customerRepository;

    public RequestSaleService(RequestSaleRepository requestSaleRepository, PropertySaleService propertySaleService, PropertySaleRepository propertySaleRepository, CustomerRepository customerRepository) {
        this.requestSaleRepository = requestSaleRepository;
        this.propertySaleRepository = propertySaleRepository;
        this.customerRepository = customerRepository;
    }

    ///////////////Get////////////////////////////////////////
    public RequestSale getRequestSaleById(Long IdDemande) {
       return requestSaleRepository.findById(IdDemande).orElseThrow(() -> new RequestNotFoundException());
    }

    public List<RequestSale> getAllRequestByCustomerId(Long customerId) {
        return requestSaleRepository.getRequestSaleByCustomer_Id(customerId);
    }

    public List<RequestSale> getAllRequestByAgentId(Long agentId) {
        return requestSaleRepository.getRequestSaleByAgent_Id(agentId);
    }

    public Customer getCustomerById(Long idUser) {
        return customerRepository.findById(idUser).orElseThrow(() -> new CustomerNotFoundException(idUser));
    }

    ////////// Verifiy///////////////////////////////
    public boolean isApplied(Long customerId, Long propertySaleId) {
        return requestSaleRepository.existsByCustomer_IdAndPropertySale_IdProperty(customerId, propertySaleId);

    }

    //////////////Create//////////////////////////////////////
    public RequestSale createRequest(RequestSale requestSale) {
        if (isApplied(requestSale.getCustomer().getId(), requestSale.getPropertySale().getIdProperty())) {
            throw new RequestAppliedSaleException(requestSale);
        }
        return requestSaleRepository.save(requestSale);
    }

    ////////////////Update | Accept, Decline////////////////////////////////////
    public RequestSale updateRequest(Long IdDemande, String status) {

        return requestSaleRepository.findById(IdDemande).map(requestSale -> {
            requestSale.setStatusDemande(status);
            return requestSaleRepository.save(requestSale);

        }).orElseThrow(() -> new RequestNotFoundException());
    }

    public RequestSale acceptRequest(Long IdDemande) {
        return updateRequest(IdDemande, requestStatus.get(0));
    }

    public RequestSale rejectRequest(Long IdDemande) {
        return updateRequest(IdDemande, requestStatus.get(1));
    }


    ////////////////Update Owner////////////////////////////////////
    public PropertySale updatePropertyOwner(Long IdDemande, Long idUser){
        Customer customer = getCustomerById(idUser);
        RequestSale requestSale = requestSaleRepository.findById(IdDemande).orElseThrow(()-> new RequestNotFoundException());
        Long idProperty = requestSale.getPropertySale().getIdProperty();

        return propertySaleRepository.findById(idProperty).map(propertySale -> {
            propertySale.setCustomer(customer);
            propertySale.setIsAvailable(false); //// retirer de l'affichage client
            return propertySaleRepository.save(propertySale);
        }).orElseThrow(() -> new PropertySaleNotFoundException(idProperty));
    }

    ////////////////Update and remove from market////////////////////////////////////
    public PropertySale acceptPropertyOwner(Long IdDemande, Long idUser){
        RequestSale requestSale = getRequestSaleById(IdDemande);

        if (!requestSale.getStatusDemande().equals(requestStatus.get(0))){
            throw new RuntimeException("Cannot change the owner of a request that has been not accepted");
        }

        PropertySale propertySale = updatePropertyOwner(IdDemande, idUser);
        return propertySale;
    }

    /////////////////Delete//////////////////////////////////////
    public void deleteRequestWithId(Long IdDemande) {

        RequestSale requestSale = getRequestSaleById(IdDemande);

        if (requestSale.getStatusDemande().equals(requestStatus.get(0))) {
            throw new RuntimeException("Cannot delete a request that has been accepted");
        } else {
            requestSaleRepository.deleteById(IdDemande);
        }
    }
}
