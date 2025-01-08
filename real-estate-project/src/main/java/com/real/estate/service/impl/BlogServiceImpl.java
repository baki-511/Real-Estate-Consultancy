package com.real.estate.service.impl;

import com.real.estate.entity.Blog;
import com.real.estate.exception.BlogNotFoundException;
import com.real.estate.payload.ApiResponse;
import com.real.estate.repository.BlogRepository;
import com.real.estate.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    
    @Override
    public Blog addBlog(Blog blog, MultipartFile file) {
        String image = getImageToString(file);
        blog.setImage(image);
        return blogRepository.save(blog);
    }
    
    @Override
    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }
    
    @Override
    public Blog getBlogById(Long blogId) {
        return blogRepository.findById(blogId)
                .orElseThrow(() -> new BlogNotFoundException(blogId));
    }
    
    @Override
    public ApiResponse deleteBlogById(Long blogId) {
        Blog blogById = getBlogById(blogId);
        blogRepository.delete(blogById);
        return new ApiResponse("Blog Deleted Successfully!", true);
    }
    
    @Override
    public Blog updateBlog(Blog blog, MultipartFile file) {
        Blog blogById = getBlogById(blog.getBlogId());
        
        if (file.isEmpty()) {
            blog.setImage(blogById.getImage());
        } else {
            String image = getImageToString(file);
            blog.setImage(image);
        }
        return blogRepository.save(blog);
    }
    
    
    private String getImageToString(MultipartFile file) {
        String image = "";
        try {
            image = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
