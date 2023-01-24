package com.example.demo.blogpost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    @Query("SELECT b.title, b.content FROM BlogPost b WHERE b.appUser.id = ?1")
    ArrayList<String> findAllByAppUserId(Long appUserId);
}
