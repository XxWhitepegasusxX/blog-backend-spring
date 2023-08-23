package com.example.blog.controllers;

import com.example.blog.dtos.PostRecordDto;
import com.example.blog.models.PostModel;
import com.example.blog.repositories.PostRepository;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostRepository postRepository;

    //READ

    @GetMapping()
    public ResponseEntity<List<PostModel>> getAllPosts(){
        List<PostModel> postList = postRepository.findAll();
        if(!postList.isEmpty()){
            for (PostModel post: postList){
                UUID id = post.getId();
                post.add(linkTo(methodOn(PostController.class).getById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value="id") UUID id){
        Optional<PostModel> post = postRepository.findById(id);
        if(post.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }
        post.get().add(linkTo(methodOn(PostController.class).getAllPosts()).withRel("all-posts"));
        return ResponseEntity.status(HttpStatus.OK).body(post.get());
    }

    // CREATE

    @PostMapping()
    public ResponseEntity<Object> savePost(@RequestBody @Valid PostRecordDto postRecordDto){
        if(postRepository.existsByTitle(postRecordDto.title())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Post already exists!");
        }
        var postModel = new PostModel();
        BeanUtils.copyProperties(postRecordDto, postModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(postRepository.save(postModel));
    }

    // UPDATE

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable(value="id") UUID id, @RequestBody @Valid PostRecordDto alteredPost){
        Optional<PostModel> postOptional = postRepository.findById(id);

        if(postOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }
        PostModel postModel = new PostModel();
        BeanUtils.copyProperties(alteredPost, postModel);
        postModel.setId(postOptional.get().getId());
        postModel.setCreatedAt(postOptional.get().getCreatedAt());
        return ResponseEntity.status(HttpStatus.OK).body(postRepository.save(postModel));
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable(value="id") UUID id){
        Optional<PostModel> post = postRepository.findById(id);
        if(post.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }
        postRepository.delete(post.get());
        return ResponseEntity.status(HttpStatus.OK).body("Post deleted!");
    }
}
