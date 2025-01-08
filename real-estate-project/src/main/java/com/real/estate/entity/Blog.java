package com.real.estate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId;
    private String title;
    @Column(length = 5000)
    private String description;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    @JsonIgnore
    private String image;
    
    
    public Blog() {
    }
    
    public Blog(Long blogId, String title, String description, String image) {
        this.blogId = blogId;
        this.title = title;
        this.description = description;
        this.image = image;
    }
    
    public Long getBlogId() {
        return blogId;
    }
    
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
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
        return "Blog{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
