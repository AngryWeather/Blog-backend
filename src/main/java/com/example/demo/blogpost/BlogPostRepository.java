package com.example.demo.blogpost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Query("SELECT b.id as id, b.title as title, b.content as content FROM BlogPost b WHERE b.appUser.id = ?1")
    ArrayList<BlogPostProjection> findAllByAppUserId(Long appUserId);

    @Query("SELECT b.id as id, b.title as title, b.content as content FROM BlogPost b")
    ArrayList<BlogPostProjection> findAllBlogPosts();
}
