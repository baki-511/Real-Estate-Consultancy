package com.real.estate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long galleryId;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    @JsonIgnore
    private String image;
    
    public Gallery() {
    }
    
    public Gallery(Long galleryId, String image) {
        this.galleryId = galleryId;
        this.image = image;
    }
    
    public Long getGalleryId() {
        return galleryId;
    }
    
    public void setGalleryId(Long galleryId) {
        this.galleryId = galleryId;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "Gallery{" +
                "galleryId=" + galleryId +
                ", image='" + image + '\'' +
                '}';
    }
}
