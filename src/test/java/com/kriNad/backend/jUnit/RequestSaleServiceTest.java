package com.kriNad.backend.jUnit;

import com.kriNad.backend.model.Request.RequestSale;
import com.kriNad.backend.model.personne.Agent;
import com.kriNad.backend.repositories.AgentRepository;
import com.kriNad.backend.repositories.RequestSaleRepository;
import com.kriNad.backend.service.RequestSaleService;
import org.apache.commons.compress.harmony.unpack200.bytecode.forms.LocalForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class RequestSaleServiceTest {



    @Mock
    private RequestSaleRepository  requestSaleRepository;


    @InjectMocks
    private RequestSaleService requestSaleService;



    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRequestsSale(){


        Agent agent = new Agent(1L, "naomi", "naomi", "naomi@gmail.com");

        RequestSale requestSale = new RequestSale("description1", "pending", "sale", agent);
        RequestSale requestSale2 = new RequestSale("description2", "accepted", "sale", agent);



        List<RequestSale> requestSaleList = Arrays.asList(requestSale, requestSale2);
        when(requestSaleRepository.getRequestSaleByAgent_Id(1L)).thenReturn(requestSaleList);


        List<RequestSale> requestSalesByAgentId = requestSaleService.getAllRequestByAgentId(1L);

        assertEquals(2, requestSalesByAgentId.size());



    }
}
