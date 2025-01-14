package com.real.estate.controller;

import com.real.estate.entity.Testimonial;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
                                 RedirectAttributes redirectAttributes) {
        Testimonial addedTestimonial = testimonialsService.addTestimonial(testimonials, imageFile);
        if (addedTestimonial != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/testimonials/all";
    }
    
    @GetMapping("/testimonials/all")
    public String allTestimonial(Model model) {
        return allTestimonial(model, 0, 2);
    }
    
    @GetMapping("/testimonials/all/{page}")
    public String allTestimonial(Model model, @PathVariable int page, int size) {
        
        List<Testimonial> allTestimonial = testimonialsService.getAllTestimonial();
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allTestimonial.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<Testimonial> subList = allTestimonial.subList(start, end);
        Page<Testimonial> testimonialPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("testimonials", testimonialPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", testimonialPage.getTotalPages());
        model.addAttribute("totalItems", testimonialPage.getTotalElements());
        model.addAttribute("size", size);
        
        return "/admin/pages/testimonial/all-testimonials";
    }
    
    @GetMapping("/testimonial/edit/{testimonialId}")
    public String editTestimonial(@PathVariable Long testimonialId, Model model) {
        Testimonial testimonialById = testimonialsService.getTestimonialById(testimonialId);
        model.addAttribute("testimonial", testimonialById);
        return "/admin/pages/testimonial/edit-testimonial";
    }
    
    @PostMapping("/testimonial/edit")
    public String editTestimonial(@ModelAttribute Testimonial testimonials,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              RedirectAttributes redirectAttributes ) {
        Testimonial updatedTestimonial = testimonialsService.updateTestimonial(testimonials, imageFile);
        if (updatedTestimonial != null) {
            redirectAttributes.addFlashAttribute("message", "Updated Successfully!");
        }
        return "redirect:/admin/testimonials/all";
    }
    
    @GetMapping("/testimonial/delete/{testimonialId}")
    public String deleteTestimonial(@PathVariable Long testimonialId, RedirectAttributes redirectAttributes) {
        ApiResponse response = testimonialsService.deleteTestimonialById(testimonialId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/testimonials/all";
    }
}
