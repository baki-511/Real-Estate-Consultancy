package com.real.estate.service;

import com.real.estate.entity.Gallery;
import com.real.estate.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {
    Gallery addGallery(MultipartFile file);
    
    List<Gallery> getAllGallery();
    
    Gallery getGalleryById(Long galleryId);
    
    ApiResponse deleteGalleryById(Long galleryId);
    
}
