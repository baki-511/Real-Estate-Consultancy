package com.real.estate.service;

import com.real.estate.entity.Blog;
import com.real.estate.payload.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    Blog addBlog(Blog blog, MultipartFile file);
    
    List<Blog> getAllBlog();
    
    Blog getBlogById(Long blogId);
    
    ApiResponse deleteBlogById(Long blogId);
    
    Blog updateBlog(Blog blog, MultipartFile file);
}
