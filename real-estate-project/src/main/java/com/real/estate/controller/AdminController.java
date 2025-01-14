package com.real.estate.controller;

import com.real.estate.entity.ContactUs;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String allContactus(Model model) {
        return allContactUs(model, 0, 5);
    }
    
    @GetMapping("/contact/all/{page}")
    public String allContactUs(Model model, @PathVariable int page, int size) {
        List<ContactUs> allContactUs = contactUsService.getAllContactUs();
        
        Pageable pageable = PageRequest.of(page, size);
        int total = allContactUs.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<ContactUs> subList = allContactUs.subList(start, end);
        Page<ContactUs> contactUsPage = new PageImpl<>(subList, pageable, total);
        
        model.addAttribute("contacts", contactUsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contactUsPage.getTotalPages());
        model.addAttribute("totalItems", contactUsPage.getTotalElements());
        model.addAttribute("size", size);
        
        
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
