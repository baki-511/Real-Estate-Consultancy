package com.real.estate.controller;

import com.real.estate.entity.Blog;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminBlogController {
    
    @Autowired
    private BlogService blogService;
    
    
    @GetMapping("/blog/add")
    public String addBlog(Model model) {
        model.addAttribute("service", new Blog());
        return "/admin/pages/blogs/add-blog";
    }
    
    @PostMapping("/blog/add")
    public String addBlog(@ModelAttribute Blog blog,
                              @RequestParam("imageFile") MultipartFile imageFile,
                          RedirectAttributes redirectAttributes) {
        Blog savedBlog = blogService.addBlog(blog, imageFile);
        if (savedBlog != null) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully!");
        }
        return "redirect:/admin/blogs/all";
    }
    
    @GetMapping("/blogs/all")
    public String allBlog(Model model) {
        model.addAttribute("blogs", blogService.getAllBlog());
        return "/admin/pages/blogs/all-blogs";
    }
    
    @GetMapping("/blog/edit/{blogId}")
    public String editBlog(@PathVariable Long blogId, Model model) {
        Blog blog = blogService.getBlogById(blogId);
        model.addAttribute("blog", blog);
        return "/admin/pages/blogs/edit-blog";
    }
    
    @PostMapping("/blog/edit")
    public String editBlog(@ModelAttribute Blog blog,
                              @RequestParam("imageFile") MultipartFile imageFile,
                           RedirectAttributes redirectAttributes ) {
        Blog updatedBlog = blogService.updateBlog(blog, imageFile);
        if (updatedBlog != null) {
            redirectAttributes.addFlashAttribute("message", "Updated Successfully!");
        }
        return "redirect:/admin/blogs/all";
    }
    
    @GetMapping("/blog/delete/{blogId}")
    public String deleteBlog(@PathVariable Long blogId, RedirectAttributes redirectAttributes) {
        ApiResponse response = blogService.deleteBlogById(blogId);
        if (response != null) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully!");
        }
        return "redirect:/admin/blogs/all";
    }
}
