package com.example.beautyboutique.Services.Blog;

import com.example.beautyboutique.Models.BlogPost;

import java.util.List;

public interface BlogService {
    public List<BlogPost> getAllBlog();
    public BlogPost updateBlog(Integer id, BlogPost updateBlog);
    public boolean deleteBlog(Integer id);

    public BlogPost createBlog(BlogPost blog);
    public BlogPost getABlogById(Integer id);


}
