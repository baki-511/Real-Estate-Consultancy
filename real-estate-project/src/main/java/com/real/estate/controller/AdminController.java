package com.real.estate.controller;

import com.real.estate.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ContactUsService contactUsService;
    
    @GetMapping("/")
    public String home() {
        return "/admin/index";
    }
    
    
    @GetMapping("/contact/all")
    public String allContactUs(Model model) {
        model.addAttribute("contacts", contactUsService.getAllContactUs());
        return "/admin/pages/contactUs/all-contactUs";
    }
    
    @GetMapping("/contact/delete/{contactId}")
    public String deleteContactUs(@PathVariable Long contactId) {
        contactUsService.deleteContactUs(contactId);
        return "redirect:/admin/contact/all";
    }
   
}
