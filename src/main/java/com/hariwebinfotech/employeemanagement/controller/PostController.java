package com.hariwebinfotech.employeemanagement.controller;

import com.hariwebinfotech.employeemanagement.dto.HttpResponse;
import com.hariwebinfotech.employeemanagement.entity.Post;
import com.hariwebinfotech.employeemanagement.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/Post/GetAllPost")
    public HttpResponse getAllPosts(){
        List<Post> post = postService.getAllPost();
        if (post.size() > 0){
            return new HttpResponse((short) 200, "Post found successfully" , post);
        }else {
            return new HttpResponse((short) 401, "Post not found" , null);
        }
    }

    @GetMapping("/Post/GetAllPostById/{postId}")
    public HttpResponse getById(@PathVariable int postId){
        Post post=  postService.getById(postId);
        if (post != null){
            return new HttpResponse((short) 200, "Post found successfully" , post);
        }else {
            return new HttpResponse((short) 401, "Post not found" , null);
        }
    }

    @PostMapping("/Post/AddPost")
    public HttpResponse addPost(@RequestBody Post post){
        if (post.getPostName() == null || post.getPostName().equals("")){
            return new HttpResponse((short) 400, "Post Tittle is null" , null);
        }if (post.getPostDescription() == null || post.getPostDescription().equals("")){
            return new HttpResponse((short) 400, "Post Description is null" , null);
        }if (post.getPostTittle() == null || post.getPostTittle().equals("")){
            return new HttpResponse((short) 400, "Post Tittle is null" , null);
        }
        for (Post posts : postService.getAllPost() ){
            if (posts.getPostName().equalsIgnoreCase(post.getPostName())){
                return new HttpResponse((short) 400, "Post Name is already exists" , null);
            }
            if (posts.getPostTittle().equalsIgnoreCase(post.getPostTittle())){
                return new HttpResponse((short) 400, "Post Tittle is already exists" , null);
            }
        }

        Post postList = postService.AddPost(post);
        return new HttpResponse((short) 200, "Post is successfully done", postList);
    }

    @DeleteMapping("/Post/DeletePost/{postId}")
    public HttpResponse deletePost(@PathVariable int postId){
        Post post = postService.getById(postId);
        if (post == null){
            return new HttpResponse((short) 401, "Id not found", null);
        }else {
            postService.deletePost(postId);
            return new HttpResponse((short) 200, "Id is found data is deleted", post);
        }
    }

    @PutMapping("/Post/UpdatePost")
    public HttpResponse updatePost(@RequestBody Post post){
        if (post.getPostName() == null || post.getPostName().equals("")){
            return new HttpResponse((short) 400, "Post Tittle is null" , null);
        }if (post.getPostDescription() == null || post.getPostDescription().equals("")){
            return new HttpResponse((short) 400, "Post Description is null" , null);
        }if (post.getPostTittle() == null || post.getPostTittle().equals("")){
            return new HttpResponse((short) 400, "Post Tittle is null" , null);
        }if (post.getPostId() == null || post.getPostId().equals("")){
            return new HttpResponse((short) 400, "Post Id is null" , null);
        }
        for (Post posts : postService.getPostDataExceptID(post.getPostId()) ){
            if (posts.getPostName().equalsIgnoreCase(post.getPostName())){
                return new HttpResponse((short) 400, "Post Name is already exists" , null);
            }
            if (posts.getPostTittle().equalsIgnoreCase(post.getPostTittle())){
                return new HttpResponse((short) 400, "Post Tittle is already exists" , null);
            }
        }
        Post postData = postService.getById(post.getPostId());
        if (postData == null){
            return new HttpResponse((short) 401, "Post Data not found", null);
        }else {
            Post postList = postService.updatePost(post);
            return new HttpResponse((short) 200, "Post is successfully updated", postList);
        }

    }
}
