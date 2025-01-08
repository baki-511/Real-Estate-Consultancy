package com.real.estate.controller;

import com.real.estate.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServicesService servicesService;
    
    
    @GetMapping("/{serviceId}")
    public String getContactUs(@PathVariable Long serviceId, Model model) {
        model.addAttribute("serviceList", servicesService.getAllServices());
        model.addAttribute("service", servicesService.getServiceById(serviceId));
        return "/pages/service";
    }
    
    
}
