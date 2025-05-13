package com.kriNad.backend.repositories;

import com.kriNad.backend.model.property.Occupant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


// nadine
public interface OccupantRepository extends JpaRepository<Occupant, Long> {

    @Query("select count(c) from Customer c where c.occupant.id = (select pr.occupant.id from PropertyRent pr where pr.idProperty = ?1)")
    Long countOccupantByProperty(Long propertyId);
}
