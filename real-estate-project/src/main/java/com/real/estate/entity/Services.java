package com.real.estate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    private String title;
    @Column(length = 2000)
    private String description;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    @JsonIgnore
    private String image;
    
    
    public Services() {
    }
    
    public Services(Long serviceId, String title, String description, String image) {
        this.serviceId = serviceId;
        this.title = title;
        this.description = description;
        this.image = image;
    }
    
    public Long getServiceId() {
        return serviceId;
    }
    
    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "Services{" +
                "serviceId=" + serviceId +
                ", title='" + title + '\'' +
                ", description='" + description + '\''+
                '}';
    }
}
