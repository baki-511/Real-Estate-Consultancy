package com.real.estate.exception;

public class ContactUsNotFoundException extends RuntimeException{
    public ContactUsNotFoundException(Long contactUsId) {
        super("Contact Us NOT Found with ID : " + contactUsId);
    }
}
