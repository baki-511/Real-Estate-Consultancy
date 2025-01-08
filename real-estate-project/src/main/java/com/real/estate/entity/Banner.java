package com.real.estate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bannerId;
    private String title;
    @Column(length = 500)
    private String description;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    @JsonIgnore
    private String image;
    
    public Banner() {
    }
    
    public Banner(Integer bannerId, String title, String description) {
        this.bannerId = bannerId;
        this.title = title;
        this.description = description;
    }
    
    public Integer getBannerId() {
        return bannerId;
    }
    
    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
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
        return "Banner{" +
                "bannerId=" + bannerId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
