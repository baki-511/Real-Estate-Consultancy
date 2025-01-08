package com.real.estate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping({"/", "/home"})
    public String home() {
        return "index";
    }
    
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    
    @GetMapping("/about")
    public String about() {
        return "/pages/about";
    }
    
    @GetMapping("/blog")
    public String blog() {
        return "/pages/blog";
    }
    
    @GetMapping("/service/1")
    public String service() {
        return "/pages/service";
    }
    
    @GetMapping("/contact")
    public String contact() {
        return "/pages/contact";
    }
}
