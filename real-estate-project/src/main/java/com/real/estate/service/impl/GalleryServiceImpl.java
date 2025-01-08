package com.real.estate.service.impl;

import com.real.estate.entity.Gallery;
import com.real.estate.exception.GalleryNotFoundException;
import com.real.estate.payload.ApiResponse;
import com.real.estate.repository.GalleryRepository;
import com.real.estate.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private GalleryRepository galleryRepository;
    
    @Override
    public Gallery addGallery(MultipartFile file) {
        Gallery gallery = new Gallery();
        String image = getImageToString(file);
        gallery.setImage(image);
        return galleryRepository.save(gallery);
    }
    
    @Override
    public List<Gallery> getAllGallery() {
        return galleryRepository.findAll();
    }
    
    @Override
    public Gallery getGalleryById(Long galleryId) {
        return galleryRepository.findById(galleryId)
                .orElseThrow(()->new GalleryNotFoundException(galleryId));
    }
    
    @Override
    public ApiResponse deleteGalleryById(Long galleryId) {
        Gallery galleryById = getGalleryById(galleryId);
        galleryRepository.delete(galleryById);
        return new ApiResponse("Gallery Image Deleted Successfully!", true);
    }
    
    
    private String getImageToString(MultipartFile file) {
        String image = "";
        try {
            image = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
