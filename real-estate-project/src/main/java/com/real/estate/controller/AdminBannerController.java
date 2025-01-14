package com.real.estate.controller;

import com.real.estate.entity.Banner;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.BannerService;
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
        return allBanner(model, 0, 5);
    }
    
    @GetMapping("/banners/all/{page}")
    public String allBanner(Model model, @PathVariable int page, int size) {
        List<Banner> allBanner = bannerService.getAllBanner();
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allBanner.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<Banner> subList = allBanner.subList(start, end);
        Page<Banner> bannerPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("banners", bannerPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bannerPage.getTotalPages());
        model.addAttribute("totalItems", bannerPage.getTotalElements());
        model.addAttribute("size", size);
        
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
