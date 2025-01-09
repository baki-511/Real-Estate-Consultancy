package com.real.estate.controller;

import com.real.estate.service.BlogService;
import com.real.estate.service.GalleryService;
import com.real.estate.service.ServicesService;
import com.real.estate.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    
    @Autowired
    private ServicesService servicesService;
    
    @Autowired
    private TestimonialService testimonialService;
    
    @Autowired
    private GalleryService galleryService;
    
    @Autowired
    private BlogService blogService;
    
    
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("testimonials", testimonialService.getAllTestimonial());
        model.addAttribute("logos", galleryService.getAllGallery());
        model.addAttribute("services", servicesService.getAllServices());
        return "index";
    }
    
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("testimonials", testimonialService.getAllTestimonial());
        model.addAttribute("logos", galleryService.getAllGallery());
        model.addAttribute("services", servicesService.getAllServices());
        return "/pages/about";
    }
    
    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("blogs", blogService.getAllBlog());
        model.addAttribute("services", servicesService.getAllServices());
        return "/pages/blog";
    }
    
    
    @GetMapping("/service/{serviceId}")
    public String getContactUs(@PathVariable Long serviceId, Model model) {
        model.addAttribute("services", servicesService.getAllServices());
        model.addAttribute("service", servicesService.getServiceById(serviceId));
        model.addAttribute("testimonials", testimonialService.getAllTestimonial());
        model.addAttribute("logos", galleryService.getAllGallery());
        return "/pages/service";
    }
    
    @GetMapping("/blog/{blogId}")
    public String getBlogById(@PathVariable Long blogId, Model model) {
        model.addAttribute("services", servicesService.getAllServices());
        model.addAttribute("blog", blogService.getBlogById(blogId));
        model.addAttribute("testimonials", testimonialService.getAllTestimonial());
        model.addAttribute("logos", galleryService.getAllGallery());
        return "/pages/blog1";
    }
  
}
