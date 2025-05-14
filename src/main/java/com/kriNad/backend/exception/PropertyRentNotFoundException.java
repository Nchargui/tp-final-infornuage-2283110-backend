package com.kriNad.backend.exception;

public class PropertyRentNotFoundException extends RuntimeException{
        public PropertyRentNotFoundException(Long id){
            super("Could not found the property rent with id "+ id);
        }
}
