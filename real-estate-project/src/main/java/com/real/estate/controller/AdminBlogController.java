package com.real.estate.controller;

import com.real.estate.entity.Blog;
import com.real.estate.payload.ApiResponse;
import com.real.estate.service.BlogService;
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
        return allBlog(model, 0, 5);
    }
    
    @GetMapping("/blogs/all/{page}")
    public String allBlog(Model model, @PathVariable int page, int size) {
      
        List<Blog> allBlog = blogService.getAllBlog();
        model.addAttribute("blogs", allBlog);
        
        // Pagination
        Pageable pageable = PageRequest.of(page, size);
        int total = allBlog.size();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), total);
        List<Blog> subList = allBlog.subList(start, end);
        Page<Blog> blogPage = new PageImpl<>(subList, pageable, total);
        
        // Attributes
        model.addAttribute("blogs", blogPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", blogPage.getTotalPages());
        model.addAttribute("totalItems", blogPage.getTotalElements());
        model.addAttribute("size", size);
        
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
