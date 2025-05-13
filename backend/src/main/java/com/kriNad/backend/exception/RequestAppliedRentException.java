package com.kriNad.backend.exception;

import com.kriNad.backend.model.Request.RequestRent;

public class RequestAppliedRentException extends RuntimeException {
  public RequestAppliedRentException(RequestRent requestRent){
    super( requestRent.getCustomer().getId() + " have already applied for this property");
  }
}
