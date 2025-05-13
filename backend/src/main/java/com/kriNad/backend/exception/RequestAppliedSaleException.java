package com.kriNad.backend.exception;

import com.kriNad.backend.model.Request.RequestSale;

public class RequestAppliedSaleException extends RuntimeException {
  public RequestAppliedSaleException(RequestSale requestSale){
    super( requestSale.getCustomer().getId() + " have already applied for this property");
  }
}
