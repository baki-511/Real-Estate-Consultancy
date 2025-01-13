package com.real.estate.controller;

import com.real.estate.entity.Gallery;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminGalleryController {
    @Autowired
    private GalleryService galleryService;
    
    @GetMapping("/gallery/all")
    public String getAllGallery(Model model) {
        model.addAttribute("galleries", galleryService.getAllGallery());
        return "/admin/pages/gallery/all-gallery";
    }
    
    @GetMapping("/gallery/add")
    public String addGallery(Model model) {
        model.addAttribute("gallery", new Gallery());
        return "/admin/pages/gallery/add-gallery";
    }
    
    @PostMapping("/gallery/add")
    public String addGalleryImg(@RequestParam("imageFile") MultipartFile imageFile,
                                RedirectAttributes redirectAttributes) {
        Gallery addedGallery = galleryService.addGallery(imageFile);
        if (addedGallery != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/gallery/all";
    }
    
    @GetMapping("/gallery/delete/{galleryId}")
    public String deleteGallery(@PathVariable Long galleryId, RedirectAttributes redirectAttributes) {
        ApiResponse apiResponse = galleryService.deleteGalleryById(galleryId);
        if (apiResponse != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/gallery/all";
    }
    
}
