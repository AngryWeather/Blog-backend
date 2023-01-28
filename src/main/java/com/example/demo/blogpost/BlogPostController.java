package com.example.demo.blogpost;

import com.example.demo.appuser.AppUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class BlogPostController {
    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping("/new-post/add")
    public String addPost(@AuthenticationPrincipal AppUser appUser, @ModelAttribute BlogPost blogPost) {
        blogPostService.addPost(appUser, blogPost);
        return "redirect:http://localhost:3000/post";
    }

    @ResponseBody
    @PostMapping("/api/new-post/add")
    public void addPostApi (@AuthenticationPrincipal AppUser appUser, @RequestBody BlogPost blogPost) {
        if (appUser != null) {
            blogPostService.addPost(appUser, blogPost);
        } else {
            throw new NullPointerException();
        }
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/posts")
    public ArrayList<BlogPostProjection> getBlogPosts(@AuthenticationPrincipal AppUser appUser) {
        return blogPostService.getBlogPosts(appUser);
    }

    @ResponseBody
    @GetMapping("/posts/all")
    public ArrayList<BlogPostProjection> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }


    @ResponseBody
    @PutMapping("/posts/{id}")
    public void updateBlogPostApi(@PathVariable("id") Long id, @RequestBody BlogPost blogPost,
                                  @AuthenticationPrincipal AppUser appUser) {
        blogPostService.updateBlogPost(id, blogPost, appUser);
    }

    @ResponseBody
    @DeleteMapping("/posts/{id}")
    public void deleteBlogPostApi(@PathVariable("id") Long id, @AuthenticationPrincipal AppUser appUser) {
        blogPostService.deleteBlogPost(id, appUser);
    }

}