package com.real.estate.controller;

import com.real.estate.entity.Banner;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminBannerController {
    @Autowired
    private BannerService bannerService;
    
    @GetMapping("/banner/add")
    public String addBanner(Model model) {
        model.addAttribute("banner", new Banner());
        return "/admin/pages/banner/add-banner";
    }
    
    @PostMapping("/banner/add")
    public String addBanner(@ModelAttribute Banner banner,
                            @RequestParam("imageFile") MultipartFile imageFile,
                            RedirectAttributes redirectAttributes) {
        Banner savedBanner = bannerService.addBanner(banner, imageFile);
        if (savedBanner != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/banners/all";
    }
    
    @GetMapping("/banners/all")
    public String allBanner(Model model) {
        model.addAttribute("banners", bannerService.getAllBanner());
        return "/admin/pages/banner/all-banner";
    }
    
    @GetMapping("/banner/edit/{bannerId}")
    public String editBanner(@PathVariable Integer bannerId, Model model) {
        Banner banner = bannerService.getBannerById(bannerId);
        model.addAttribute("banner", banner);
        return "/admin/pages/banner/edit-banner";
    }
    
    @PostMapping("/banner/edit")
    public String editBanner(@ModelAttribute Banner banner,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             RedirectAttributes redirectAttributes) {
        Banner updateBanner = bannerService.updateBanner(banner, imageFile);
        if (updateBanner != null) {
            redirectAttributes.addFlashAttribute("message", "Updated Successfully!");
        }
        return "redirect:/admin/banners/all";
    }
    
    @GetMapping("/banner/delete/{bannerId}")
    public String deleteBanner(@PathVariable Integer bannerId, RedirectAttributes redirectAttributes) {
        ApiResponse response = bannerService.deleteBannerById(bannerId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/banners/all";
    }
    
}
