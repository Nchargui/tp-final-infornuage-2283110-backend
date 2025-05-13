package com.kriNad.backend.repositories;

import com.kriNad.backend.model.Request.RequestRent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// nadine
public interface RequestRentRepository extends JpaRepository<RequestRent, Long> {

    public boolean existsByCustomer_IdAndPropertyRent_IdProperty(Long customerId, Long propertyRentId);

    public List<RequestRent> getRequestRentByAgent_Id(Long agentId);

    public List<RequestRent> getRequestRentByCustomer_Id(Long customerId);
}
