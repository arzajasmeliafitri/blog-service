package com.cimb.arza.javatest.controller;

import com.cimb.arza.javatest.entity.Blog;
import com.cimb.arza.javatest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> todos = blogService.getBlogs();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping({"/{blogId}"})
    public ResponseEntity<Object> getBlog(@PathVariable Long blogId) {
        return new ResponseEntity<>(blogService.getBlogById(blogId), HttpStatus.OK);
    }


    @PostMapping("/create-blog")
    public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) {
        Blog blog1 = blogService.insert(blog);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(blog1, httpHeaders, HttpStatus.CREATED);
    }


    @PutMapping({"/{blogId}"})
    public ResponseEntity<Object> updateTodo(@PathVariable("blogId") Long blogId, @RequestBody Blog blog) {
        blogService.updateBlog(blogId, blog);
        return new ResponseEntity<>(blogService.getBlogById(blogId), HttpStatus.OK);
    }


    @DeleteMapping({"/{blogId}"})
    public ResponseEntity<Blog> deleteTodo(@PathVariable("blogId") Long blogId) {
        blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
