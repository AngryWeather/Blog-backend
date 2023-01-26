package com.example.demo.blogpost;

import com.example.demo.appuser.AppUser;
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

}