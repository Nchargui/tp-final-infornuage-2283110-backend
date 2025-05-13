package com.kriNad.backend.service;

import com.kriNad.backend.model.property.ImagePropertyRent;
import com.kriNad.backend.repositories.ImagePropertyRentRepository;

import org.springframework.stereotype.Service;
import java.util.List;

// nadine
@Service
public class ImagePropertyRentService {

    private final ImagePropertyRentRepository imagePropertyRentRepository;

    public ImagePropertyRentService(ImagePropertyRentRepository imagePropertyRentRepository) {
        this.imagePropertyRentRepository = imagePropertyRentRepository;
    }

    public List<ImagePropertyRent> getAll(){
        return imagePropertyRentRepository.findAll();
    }

    public List<String> getAllImagesByPropertyId(Long propertyIdProperty){
        return imagePropertyRentRepository.getImagePropertyRentByPropertyRentId(propertyIdProperty);
    }
}
