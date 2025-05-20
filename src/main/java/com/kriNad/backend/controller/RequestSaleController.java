package com.kriNad.backend.controller;

import com.kriNad.backend.model.Request.RequestSale;
import com.kriNad.backend.service.RequestSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Nadine
@RestController
@RequestMapping("api/RequestSale")
@CrossOrigin
public class RequestSaleController {

    @Autowired
     RequestSaleService requestSaleService;

    @PostMapping("/createRequest")
    public RequestSale createRequest(@RequestBody RequestSale requestSale){
        return requestSaleService.createRequest(requestSale);
    }

    @GetMapping("/agent/{id}")
    public List<RequestSale> getAllRequestsByAgentId(@PathVariable Long id){
        return requestSaleService.getAllRequestByAgentId(id);
    }

    @GetMapping("/customer/{id}")
    public List<RequestSale> getAllRequestsByCustomerId(@PathVariable Long id){
        return requestSaleService.getAllRequestByCustomerId(id);
    }

    @GetMapping("verifyRequest/{customerId}/{propertySaleId}")
    public boolean verifyRequest(@PathVariable Long customerId,@PathVariable Long propertySaleId){
        return requestSaleService.isApplied(customerId,propertySaleId);
    }

    @DeleteMapping("/deleteRequest/{IdDemande}")
    public void deleteRequest(@PathVariable Long IdDemande){
        requestSaleService.deleteRequestWithId(IdDemande);
    }

    @PutMapping("/accept/{idDemande}")
    public void acceptRequest(@PathVariable Long idDemande){
        requestSaleService.acceptRequest(idDemande);
    }

    @PutMapping("/refuse/{idDemande}")
    public void rejectRequest(@PathVariable Long idDemande){
        requestSaleService.rejectRequest(idDemande);
    }

    @PutMapping("/updatePerson/{idDemande}/{idUser}")
    public void updateOwner(@PathVariable Long idDemande, @PathVariable Long idUser){
        requestSaleService.acceptPropertyOwner(idDemande, idUser);
    }
}
