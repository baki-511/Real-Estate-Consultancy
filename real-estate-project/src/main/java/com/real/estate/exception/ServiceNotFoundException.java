package com.real.estate.exception;

public class ServiceNotFoundException extends RuntimeException{
    public ServiceNotFoundException(Long serviceId) {
        super("Service NOT Found With ID : " + serviceId);
    }
}
