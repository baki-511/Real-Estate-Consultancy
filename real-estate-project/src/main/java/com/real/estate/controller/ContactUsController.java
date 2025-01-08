package com.real.estate.controller;

import com.real.estate.entity.ContactUs;
import com.real.estate.service.ContactUsService;
import com.real.estate.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactUsController {
    @Autowired
    private ContactUsService contactUsService;
    
    @Autowired
    private ServicesService servicesService;
    
    @GetMapping("")
    public String contactUs(Model model) {
        model.addAttribute("serviceList", servicesService.getAllServices());
        return "/pages/contact";
    }
   
    @PostMapping("/save")
    public String saveContact(@ModelAttribute ContactUs contactUs) {
        contactUsService.addContactUs(contactUs);
        return "redirect:/contact";
    }
}
