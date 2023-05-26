package com.hariwebinfotech.employeemanagement.service;

import com.hariwebinfotech.employeemanagement.entity.Employee;
import com.hariwebinfotech.employeemanagement.entity.Post;
import com.hariwebinfotech.employeemanagement.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public List<Post> getAllPost(){
        return postRepo.findAll();
    }

    public Post AddPost(Post posts){
        return postRepo.save(posts);
    }

    public String deletePost(int postId){
        postRepo.deleteById(postId);
        return "";
    }
    public Post getById(int postId){
        return postRepo.findById(postId).orElse(null);
    }

    public List<Post> getPostDataExceptID(Integer postId){
        return postRepo.getPostDataExceptID(postId);
    }

    public Post updatePost(Post post){
        Post existingPost = postRepo.findById(post.getPostId()).orElse(null);
        existingPost.setPostName(post.getPostName());
        existingPost.setPostDescription(post.getPostDescription());
        existingPost.setPostTittle(post.getPostTittle());
        return postRepo.save(existingPost);
    }
}
