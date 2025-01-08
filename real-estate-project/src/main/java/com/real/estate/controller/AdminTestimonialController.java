package com.real.estate.controller;

import com.real.estate.entity.Testimonial;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminTestimonialController {
    
    @Autowired
    private TestimonialService testimonialsService;
    
    
    @GetMapping("/testimonial/add")
    public String addService(Model model) {
        model.addAttribute("testimonial", new Testimonial());
        return "/admin/pages/testimonial/add-testimonial";
    }
    
    @PostMapping("/testimonial/add")
    public String addTestimonial(@ModelAttribute Testimonial testimonials,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              Model model) {
        testimonialsService.addTestimonial(testimonials, imageFile);
        return "redirect:/admin/testimonials/all";
    }
    
    @GetMapping("/testimonials/all")
    public String allService(Model model) {
        model.addAttribute("testimonials", testimonialsService.getAllTestimonial());
        return "/admin/pages/testimonial/all-testimonials";
    }
    
    @GetMapping("/testimonial/edit/{testimonialId}")
    public String editService(@PathVariable Long testimonialId, Model model) {
        Testimonial testimonialById = testimonialsService.getTestimonialById(testimonialId);
        model.addAttribute("testimonial", testimonialById);
        return "/admin/pages/testimonial/edit-testimonial";
    }
    
    @PostMapping("/testimonial/edit")
    public String editService(@ModelAttribute Testimonial testimonials,
                              @RequestParam("imageFile") MultipartFile imageFile ) {
        testimonialsService.updateTestimonial(testimonials, imageFile);
        return "redirect:/admin/testimonials/all";
    }
    
    @GetMapping("/testimonial/delete/{testimonialId}")
    public String deleteService(@PathVariable Long testimonialId) {
        ApiResponse response = testimonialsService.deleteTestimonialById(testimonialId);
        return "redirect:/admin/testimonials/all";
    }
}
