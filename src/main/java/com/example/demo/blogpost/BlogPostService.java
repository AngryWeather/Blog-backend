package com.example.demo.blogpost;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;
    private final AppUserRepository appUserRepository;

    public BlogPostService(BlogPostRepository blogPostRepository, AppUserRepository appUserRepository) {
        this.blogPostRepository = blogPostRepository;
        this.appUserRepository = appUserRepository;
    }

    public void addPost(AppUser appUser, BlogPost blogPost) {

        blogPost.setAppUser(appUser);
        blogPostRepository.save(blogPost);
    }

    public ArrayList<BlogPostProjection> getBlogPosts(AppUser appUser) {
        return blogPostRepository.findAllByAppUserId(appUser.getId());
    }

    public ArrayList<BlogPostProjection> getAllBlogPosts() {
        return blogPostRepository.findAllBlogPosts();
    }


    public void updateBlogPost(Long id, BlogPost blogPost, AppUser appUser) {
        if (blogPostRepository.existsById(id)) {
            Optional<BlogPost> currentBlogPost = blogPostRepository.findById(id);
            if (currentBlogPost.get().getAppUser().equals(appUser)) {
                currentBlogPost.get().setTitle(blogPost.getTitle());
                currentBlogPost.get().setContent(blogPost.getContent());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
    }

    public void deleteBlogPost(Long id, AppUser appUser) {
        if (blogPostRepository.existsById(id)) {
            Optional<BlogPost> blogPost = blogPostRepository.findById(id);
            if ( blogPost.get().getAppUser().equals(appUser)) {
                blogPostRepository.deleteById(id);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
    }
}
