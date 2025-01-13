package com.real.estate.controller;

import com.real.estate.payload.ApiResponse;
import com.real.estate.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String deleteContactUs(@PathVariable Long contactId, RedirectAttributes redirectAttributes) {
        ApiResponse apiResponse = contactUsService.deleteContactUs(contactId);
        if (apiResponse != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/contact/all";
    }
   
}
