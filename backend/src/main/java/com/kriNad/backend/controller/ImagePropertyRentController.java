package com.kriNad.backend.controller;

import com.kriNad.backend.model.property.ImagePropertyRent;
import com.kriNad.backend.service.ImagePropertyRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Nadine
@RestController
@RequestMapping("/ImagePropertyRent")
@CrossOrigin
public class ImagePropertyRentController {

    @Autowired
    ImagePropertyRentService imagePropertyRentService;

    @GetMapping("/getAllImagesByPropertyId")
    public List<String> getAllImagesByPropertyId(@RequestParam Long propertyIdProperty){
        return imagePropertyRentService.getAllImagesByPropertyId(propertyIdProperty);
    }
}
