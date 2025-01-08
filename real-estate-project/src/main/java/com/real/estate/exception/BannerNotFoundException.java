package com.real.estate.exception;

public class BannerNotFoundException extends RuntimeException{
    public BannerNotFoundException(Integer bannerId) {
        super("Banner NOT Found With ID : " + bannerId);
    }
    
    public BannerNotFoundException(String msg) {
        super(msg);
    }
}
