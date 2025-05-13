package com.kriNad.backend.exception;

public class PropertySaleNotFoundException extends RuntimeException{
        public PropertySaleNotFoundException(Long id){
            super("Could not found the property sale with id "+ id);
        }
}
