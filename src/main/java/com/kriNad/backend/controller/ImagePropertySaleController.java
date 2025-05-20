package com.kriNad.backend.controller;

import com.kriNad.backend.model.property.ImagePropertySale;
import com.kriNad.backend.service.ImagePropertySaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Nadine
@RestController
@RequestMapping("api/ImagePropertySale")
@CrossOrigin

public class ImagePropertySaleController {

    @Autowired
    ImagePropertySaleService imagePropertySaleService;

    @GetMapping("/getAllImagesByPropertyId")
    public List<String> getAllImagesByPropertyId(Long propertyIdProperty){
        return imagePropertySaleService.getAllImagesByPropertyId(propertyIdProperty);
    }
}

