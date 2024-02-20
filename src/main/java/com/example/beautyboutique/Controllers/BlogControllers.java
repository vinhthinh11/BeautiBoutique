package com.example.beautyboutique.Controllers;

import com.example.beautyboutique.DTOs.Requests.Blog.BlogRequest;
import com.example.beautyboutique.DTOs.Responses.Blog.PageBlog;
import com.example.beautyboutique.DTOs.Responses.Cart.CartResponse;
import com.example.beautyboutique.Models.BlogImage;
import com.example.beautyboutique.Models.BlogPost;
import com.example.beautyboutique.Models.Comment;
import com.example.beautyboutique.Services.Blog.BlogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "http://localhost:3000")
public class BlogControllers {
    @Autowired
    BlogServices blogServices;

    @GetMapping(value = "/get-all-blog")
    public ResponseEntity<?> getAllBlog() {
        try {
            List<BlogPost> blogPostList = blogServices.getAllBlog();
            return new ResponseEntity<>(new PageBlog("Get All Blog Successfully", blogPostList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving information failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/create-blog/", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE
    }, produces = {MediaType.APPLICATION_JSON_VALUE
    })
    public @ResponseBody ResponseEntity<?> createBlog(BlogPost blog,
                                                      @RequestParam(value = "imageIds") List<String> imageIds,
                                                      @RequestParam(value = "imageUrls") List<String> imageUrls) {
        try {
            if (imageIds == null || imageUrls == null || imageIds.size() != imageUrls.size()) {
                return new ResponseEntity<>("Invalid imageIds or imageUrls", HttpStatus.BAD_REQUEST);
            }
            BlogPost createdBlog = blogServices.createBlog(blog);
            if (createdBlog != null) {
                IntStream.range(0, imageIds.size()).forEach(index -> {
                    String imageId = imageIds.get(index);
                    String imageUrl = imageUrls.get(index);
                    BlogImage image = new BlogImage();
                    image.setId(imageId);
                    image.setImageUrl(imageUrl);
                    image.setBlogPost(createdBlog);
                    blogServices.createBlogImage(image);
                });


                return new ResponseEntity<>("Created a successful blog post", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to create blog post", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Created a failed blog post", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete-blog")
    public ResponseEntity<?> deleteBlog(@RequestParam(value = "id") Integer id,
                                        @RequestParam(value = "userId") Integer userId) {
        try {
            System.out.println(id);
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Invalid blog ID");
            }
            BlogPost blog = blogServices.getABlogById(id);
            int ownerId = blog.getUser().getId();
            if (userId == ownerId) {
                boolean isDelete = blogServices.deleteBlog(id);
                if (isDelete)
                    return new ResponseEntity<>("Delete blog successfully!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Delete blog fail!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Delete blog fail!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update-blog")
    public @ResponseBody ResponseEntity<?> updateBlog(BlogPost blogPost,
                                                      @RequestParam(value = "id") Integer id,
                                                      @RequestParam(value = "userId") Integer userId) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest().body("Invalid comment ID");
            }
            BlogPost blog = blogServices.getABlogById(id);
            int ownerId = blog.getUser().getId();
            if (userId == ownerId) {
                BlogPost updatedBlogResult = blogServices.updateBlog(id, blogPost);
                if (updatedBlogResult != null) {
                    return new ResponseEntity<>("Updated comment successfully", HttpStatus.OK);
                }
                return new ResponseEntity<>("Failed to update comment", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server!", HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
