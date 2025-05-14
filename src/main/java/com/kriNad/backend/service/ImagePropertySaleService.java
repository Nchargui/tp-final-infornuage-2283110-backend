package com.kriNad.backend.service;

import com.kriNad.backend.model.property.ImagePropertySale;
import com.kriNad.backend.repositories.ImagePropertySaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// nadine
@Service
public class ImagePropertySaleService {

    private final ImagePropertySaleRepository imagePropertySaleRepository;

    public ImagePropertySaleService(ImagePropertySaleRepository imagePropertySaleRepository) {
        this.imagePropertySaleRepository = imagePropertySaleRepository;
    }

    public List<ImagePropertySale> getAll(){
        return imagePropertySaleRepository.findAll();
    }

    public List<String> getAllImagesByPropertyId(Long propertyIdProperty){
        return imagePropertySaleRepository.getImagePropertySaleByPropertySaleId(propertyIdProperty);
    }
}

