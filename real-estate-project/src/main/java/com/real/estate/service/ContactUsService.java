package com.real.estate.service;

import com.real.estate.entity.ContactUs;
import com.real.estate.payload.ApiResponse;

import java.util.List;

public interface ContactUsService {
    public ContactUs addContactUs(ContactUs contactUs);
    
    public List<ContactUs> getAllContactUs();
    
    public ContactUs getContactUsById(Long contactUsId);
    
    public ApiResponse deleteContactUs(Long contactUsId);
    
    public ContactUs updateContactUs(ContactUs contactUs);
    
}
