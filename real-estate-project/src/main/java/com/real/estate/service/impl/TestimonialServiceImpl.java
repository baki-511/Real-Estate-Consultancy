package com.real.estate.service.impl;

import com.real.estate.entity.Testimonial;
import com.real.estate.exception.TestimonialNotFoundException;
import com.real.estate.payload.ApiResponse;
import com.real.estate.repository.TestimonialRepository;
import com.real.estate.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class TestimonialServiceImpl implements TestimonialService {
    @Autowired
    private TestimonialRepository testimonialRepository;
    
    @Override
    public Testimonial addTestimonial(Testimonial testimonial, MultipartFile file) {
        String image = getImageToString(file);
        testimonial.setImage(image);
        return testimonialRepository.save(testimonial);
    }
    
    @Override
    public List<Testimonial> getAllTestimonial() {
        return testimonialRepository.findAll();
    }
    
    @Override
    public Testimonial getTestimonialById(Long testimonialId) {
        return testimonialRepository.findById(testimonialId)
                .orElseThrow(()->new TestimonialNotFoundException(testimonialId));
    }
    
    @Override
    public ApiResponse deleteTestimonialById(Long testimonialId) {
        Testimonial testimonialById = getTestimonialById(testimonialId);
        testimonialRepository.delete(testimonialById);
        return new ApiResponse("Testimonial Deleted Successfully!", true);
    }
    
    @Override
    public Testimonial updateTestimonial(Testimonial testimonial, MultipartFile file) {
        Testimonial testimonialById = getTestimonialById(testimonial.getTestimonialId());
        if (file.isEmpty()) {
            testimonial.setImage(testimonialById.getImage());
        }else {
            String image = getImageToString(file);
            testimonial.setImage(image);
        }
        return testimonialRepository.save(testimonial);
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
