package com.cimb.arza.javatest.service;

import com.cimb.arza.javatest.entity.Blog;

import java.util.List;

public interface BlogService {


    List<Blog> getBlogs();

    Object getBlogById(Long id);

    Blog insert(Blog todo);

    void updateBlog(Long id, Blog todo);

    void deleteBlog(Long todoId);
}
