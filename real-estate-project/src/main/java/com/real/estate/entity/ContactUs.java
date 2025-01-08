package com.real.estate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactUsId;
    private String name;
    private String email;
    private String venue;
    private String mobile;
    private String message;
    
    public ContactUs() {
    }
    
    public ContactUs(Long contactUsId, String name, String email, String venue, String mobile, String message) {
        this.contactUsId = contactUsId;
        this.name = name;
        this.email = email;
        this.venue = venue;
        this.mobile = mobile;
        this.message = message;
    }
    
    public Long getContactUsId() {
        return contactUsId;
    }
    
    public void setContactUsId(Long contactUsId) {
        this.contactUsId = contactUsId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getVenue() {
        return venue;
    }
    
    public void setVenue(String venue) {
        this.venue = venue;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
