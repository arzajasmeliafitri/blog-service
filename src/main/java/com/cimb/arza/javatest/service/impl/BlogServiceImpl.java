package com.cimb.arza.javatest.service.impl;

import com.cimb.arza.javatest.entity.Blog;
import com.cimb.arza.javatest.model.ErrorMessage;
import com.cimb.arza.javatest.repository.BlogRepository;
import com.cimb.arza.javatest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> getBlogs() {
        List<Blog> blogs = new ArrayList<>();
        blogRepository.findAll().forEach(blogs::add);
        return blogs;
    }

    @Override
    public Object getBlogById(Long id) {

        try {
            return blogRepository.findById(id).get();
        }catch (Exception e){
            return new ErrorMessage(e.getMessage());
        }

    }

    @Override
    public Blog insert(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void updateBlog(Long id, Blog blog) {
        Blog blogFromDb = blogRepository.findById(id).get();
        System.out.println(blogFromDb.toString());
        blogFromDb.setTitle(blog.getTitle());
        blogFromDb.setBody(blog.getBody());
        blogFromDb.setTitle(blog.getTitle());
        blogRepository.save(blogFromDb);
    }

    @Override
    public void deleteBlog(Long blogId) {
        blogRepository.deleteById(blogId);
    }
}
