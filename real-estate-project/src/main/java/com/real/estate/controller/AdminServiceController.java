package com.real.estate.controller;

import com.real.estate.entity.Services;
import com.real.estate.entity.Testimonial;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.ServicesService;
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
public class AdminServiceController {
    
    @Autowired
    private ServicesService servicesService;
    
    
    @GetMapping("/services/add")
    public String addService(Model model) {
        model.addAttribute("service", new Services());
        return "/admin/pages/services/add-services";
    }
    
    @PostMapping("/service/add")
    public String addServices(@ModelAttribute Services services,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              RedirectAttributes redirectAttributes) {
        Services addedService = servicesService.addService(services, imageFile);
        if (addedService != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/services/all";
    }
    
    @GetMapping("/services/all")
    public String allService(Model model) {
        return allService(model, 0, 2);
    }
    
    @GetMapping("/services/all/{page}")
    public String allService(Model model, @PathVariable int page, int size) {
        
        List<Services> allService = servicesService.getAllServices();
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allService.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<Services> subList = allService.subList(start, end);
        Page<Services> servicePage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("services", servicePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", servicePage.getTotalPages());
        model.addAttribute("totalItems", servicePage.getTotalElements());
        model.addAttribute("size", size);
        
        return "/admin/pages/services/all-services";
    }
    
    @GetMapping("/service/edit/{serviceId}")
    public String editService(@PathVariable Long serviceId, Model model) {
        Services serviceById = servicesService.getServiceById(serviceId);
        model.addAttribute("service", serviceById);
        return "/admin/pages/services/edit-services";
    }
    
    @PostMapping("/service/edit")
    public String editService(@ModelAttribute Services services,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              RedirectAttributes redirectAttributes ) {
        Services updatedService = servicesService.updateService(services, imageFile);
        if (updatedService != null) {
            redirectAttributes.addFlashAttribute("message", "Updated Successfully!");
        }
        return "redirect:/admin/services/all";
    }
    
    @GetMapping("/service/delete/{serviceId}")
    public String deleteService(@PathVariable Long serviceId, RedirectAttributes redirectAttributes) {
        ApiResponse response = servicesService.deleteServiceById(serviceId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/services/all";
    }
}
