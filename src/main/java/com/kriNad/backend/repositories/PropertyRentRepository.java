package com.kriNad.backend.repositories;

import com.kriNad.backend.model.property.PropertyRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// nadine et kristina
public interface PropertyRentRepository extends JpaRepository<PropertyRent, Long> {

    @Query("SELECT pr FROM PropertyRent pr WHERE pr.agent.idUser = ?1")
    List<PropertyRent> getPropertyByAgent(Long idUser);

    @Query("SELECT pr FROM PropertyRent pr WHERE pr.customer.idUser = ?1")
    List<PropertyRent> getPropertyByCustomer(Long customerId);

    @Query("SELECT pr FROM PropertyRent pr WHERE pr.agent IS NULL and pr.isAccepted is NULL")
    List<PropertyRent> findUnassignedPendingRents();

    @Query("SELECT pr FROM PropertyRent pr WHERE pr.isAccepted = true AND " +
            "(?1 IS NULL OR ?1 = '' OR pr.categorie = ?1) AND " +
            "((?2 IS NULL OR ?3 IS NULL) OR pr.price BETWEEN ?2 AND ?3) AND " +
            "(?4 IS NULL OR pr.nbRooms >= ?4) AND " +
            "(?5 IS NULL OR pr.nbBathrooms >= ?5) AND " +
            "(?6 IS NULL OR pr.nbParkingSpace >= ?6) AND " +
            "(?7 IS NULL OR pr.nbGarages >= ?7) AND " +
            "((?8 IS NULL OR ?9 IS NULL) OR pr.area BETWEEN ?8 AND ?9) AND " +
            "((?10 IS NULL OR ?11 IS NULL) OR pr.ConstructionYear BETWEEN ?10 AND ?11) AND " +
            "(?12 IS NULL OR ?12 = '' OR pr.city = ?12)")
    List<PropertyRent> findPropertyFilters(String categorie, Long minPrice, Long maxPrice, Long nbRooms, Long nbBathrooms, Long nbParking, Long nbGarages, Long minArea, Long maxArea, Long minYear, Long maxYear, String city);
}
