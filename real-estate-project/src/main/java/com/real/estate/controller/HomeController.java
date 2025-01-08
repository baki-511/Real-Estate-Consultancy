package com.real.estate.controller;

import com.real.estate.service.BlogService;
import com.real.estate.service.GalleryService;
import com.real.estate.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
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
        return "/pages/about";
    }
    
    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("blogs", blogService.getAllBlog());
        return "/pages/blog";
    }
    
    @GetMapping("/service/1")
    public String service(Model model) {
        model.addAttribute("testimonials", testimonialService.getAllTestimonial());
        model.addAttribute("logos", galleryService.getAllGallery());
        return "/pages/service";
    }
  
}
