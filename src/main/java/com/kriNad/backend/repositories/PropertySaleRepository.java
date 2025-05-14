package com.kriNad.backend.repositories;

import com.kriNad.backend.model.property.PropertySale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//nadine et kristina
public interface PropertySaleRepository extends JpaRepository<PropertySale, Long> {

    @Query("select ps from PropertySale ps" +
            " where ps.isAccepted = true" +
            " and (?1 is null or ?1 = '' or ps.categorie =?1)" +
            " and ((?2 is null or ?3 is null) or ps.price between ?2 and ?3)" +
            " and (?4 is null or ps.nbRooms >= ?4)" +
            " and (?5 is null or ps.nbBathrooms >= ?5)" +
            " and (?6 is null or ps.nbParkingSpace >= ?6)" +
            " and (?7 is null or ps.nbGarages >= ?7)" +
            " and ((?8 is null or ?9 is null) or ps.area between ?8 and ?9)" +
            " and ((?10 is null  or ?11 is null) or ps.ConstructionYear between ?10 and ?11)" +
            " and (?12 is null or ?12 = '' or ps.city = ?12)")
    List<PropertySale> findPropertyFilters(String categorie, Long minPrice, Long maxPrice, Long nbRooms, Long nbBathrooms, Long nbParking, Long nbGarages, Long minArea, Long maxArea, Long minYear, Long maxYear, String city);

    @Query("select ps from PropertySale ps where ps.agent.idUser =?1")
    List<PropertySale> getPropertyByAgent(Long idUser);

    @Query("select ps from PropertySale ps where ps.customer.idUser = ?1")
    List<PropertySale> getPropertyByCustomer(Long customerId);

    @Query("SELECT ps FROM PropertySale ps WHERE ps.agent IS NULL AND ps.isAccepted IS NULL")
    List<PropertySale> findUnassignedPendingSales();
}
