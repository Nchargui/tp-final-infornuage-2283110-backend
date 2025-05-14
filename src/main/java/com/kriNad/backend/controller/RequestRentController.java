package com.kriNad.backend.controller;

import com.kriNad.backend.model.Request.RequestRent;
import com.kriNad.backend.service.RequestRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Nadine
@RestController
@RequestMapping("/RequestRent")
@CrossOrigin
public class RequestRentController {

    @Autowired
     RequestRentService requestRentService;

    @PostMapping("/createRequest")
    public RequestRent createRequest(@RequestBody RequestRent requestRent){
        return requestRentService.createRequest(requestRent);
    }

    @GetMapping("/agent/{id}")
    public List<RequestRent> getAllRequestsByAgentId(@PathVariable Long id){
        return requestRentService.getAllRequestByAgentId(id);
    }

    @GetMapping("/customer/{id}")
    public List<RequestRent> getAllRequestsByCustomerId(@PathVariable Long id){
        return requestRentService.getAllRequestByCustomerId(id);
    }

    @GetMapping("verifyRequest/{customerId}/{propertyRentId}")
    public boolean verifyRequest(@PathVariable Long customerId,@PathVariable  Long propertyRentId){
        return requestRentService.isApplied(customerId,propertyRentId);
    }

    @DeleteMapping("/deleteRequest/{IdDemande}")
    public void deleteRequest(@PathVariable Long IdDemande){
        requestRentService.deleteRequestWithId(IdDemande);
    }

    @PutMapping("/accept/{idDemande}")
    public void acceptRequest(@PathVariable Long idDemande){
        requestRentService.acceptRequest(idDemande);
    }

    @PutMapping("/refuse/{idDemande}")
    public void rejectRequest(@PathVariable Long idDemande){
        requestRentService.rejectRequest(idDemande);
    }

    @PutMapping("/updatePerson/{idDemande}/{idUser}")
    public void updateOwner(@PathVariable Long idDemande, @PathVariable Long idUser){
        requestRentService.updateMaxOccupant(idDemande, idUser);
    }
}
