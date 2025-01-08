package com.real.estate.exception;

public class BlogNotFoundException extends RuntimeException{
    public BlogNotFoundException(Long blogId) {
        super("Blog NOT Found With ID : " + blogId);
    }
}
