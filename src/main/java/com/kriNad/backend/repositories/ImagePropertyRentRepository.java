package com.kriNad.backend.repositories;

import com.kriNad.backend.model.property.ImagePropertyRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//nadine
public interface ImagePropertyRentRepository extends JpaRepository<ImagePropertyRent, Long> {

    @Query("select im.propertyRent.idProperty, im.imageLink from ImagePropertyRent im where im.propertyRent.idProperty=?1")
    public List<String> getImagePropertyRentByPropertyRentId(Long propertyRentId);
}
