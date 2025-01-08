package com.real.estate.controller;

import com.real.estate.entity.Services;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                              Model model) {
        System.out.println(services);
        servicesService.addService(services, imageFile);
        return "redirect:/admin/services/all";
    }
    
    @GetMapping("/services/all")
    public String allService(Model model) {
        model.addAttribute("services", servicesService.getAllServices());
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
                              @RequestParam("imageFile") MultipartFile imageFile ) {
        servicesService.updateService(services, imageFile);
        return "redirect:/admin/services/all";
    }
    
    @GetMapping("/service/delete/{serviceId}")
    public String deleteService(@PathVariable Long serviceId) {
        ApiResponse response = servicesService.deleteServiceById(serviceId);
        return "redirect:/admin/services/all";
    }
}
