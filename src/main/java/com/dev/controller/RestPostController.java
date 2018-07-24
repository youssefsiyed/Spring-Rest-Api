package com.dev.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.entity.Post;
import com.dev.exception.UserNotFoundException;
import com.dev.repository.PostRepository;

@RestController
@RequestMapping(path="/api")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class RestPostController {
	
	//// Log the result in the terminal
    private final Logger logger = LoggerFactory.getLogger(RestPostController.class);
    
	private PostRepository postRepository;
	
	@Autowired	
	public RestPostController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	/////////////////GET METHOD//////////////////////////////
	@GetMapping("/posts")
	public List<Post> AfficherPost(){
		logger.info("GET the lists of all the posts SUCCESS");
		return postRepository.findAll();
		// return new ResponseEntity<>(postRepository.findAll(),HttpStatus.OK);

	}
	/////////////////GET METHOD WITH PAGINATION//////////////////////////////
	@GetMapping("/posts/pages")
	public Page<Post> AfficherPostPagination(Pageable pageable){
	logger.info("GET the lists of all the posts SUCCESS");
	return postRepository.findAll(pageable);
	// return new ResponseEntity<>(postRepository.findAll(),HttpStatus.OK);
	}
	
	////////////////SAVE METHOD///////////////////////////////
	@PostMapping("/posts")
	public Post SavePost(@Valid @RequestBody Post post){
	logger.info("POST to save a new Post SUCCESS");
	return postRepository.save(post);
	// return new ResponseEntity<>(postRepository.save(post),HttpStatus.OK);
	}
	
	//////////////////SAVE METHOD///////////////////////////////
	@PostMapping("/postsURI")
	public ResponseEntity<Object> SavePostWithResponse(@RequestBody Post post){
	Post SavedPost =postRepository.save(post);
	 postRepository.save(post);
	 
	 URI location = ServletUriComponentsBuilder
	.fromCurrentContextPath()
	.path("/{id}")
	.buildAndExpand(SavedPost.getIdPost()).toUri();
	return ResponseEntity.created(location).build();
	} 
	
	/////////////////////GET BY ID METHOD///////////////////////
	@GetMapping("/posts/{id}")
	public Resource<Optional<Post>> AfficherAvecId(@PathVariable(name="id") Long id){
	Optional<Post> post= postRepository.findById(id);
	logger.info("GET only one id post SUCCESS");
	////////////////////IF POST NOT FOUND//////////////////////////
	if (!post.isPresent()){
	logger.info("GET to get the id post FAILED");
	throw new UserNotFoundException("Not Found this id:"+id);
	// return new ResponseEntity<>(postRepository.findById(id),HttpStatus.NOT_FOUND);
	//return post.get().getUser();
	}
	/////////////ALL Posts HATEOAS ///////////
	Resource<Optional<Post>> resource = new Resource<Optional<Post>>(post);
	ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).AfficherPost());
	resource.add(linkTo.withRel("all-posts"));
	return resource;
	}
	
	/////////////////GET METHOD VERSION//////////////////////////////
	@GetMapping(value="/posts/{id}/header",headers="X-API-VERSION=1") // params="version=1"
	public Optional<Post> AfficherPostVersion(@PathVariable("id") Long id){
	return postRepository.findById(id);
	// return new ResponseEntity("Post find successfully", HttpStatus.OK);
	}
	
	////////////////MODIFICATION METHOD///////////////////////////////
	@PutMapping("/posts/{id}")
	public Post UpdatePost(@RequestBody Post post,@PathVariable long id){
	logger.info("PUT to update Post SUCCESS");
	post.setIdPost(id);
	return postRepository.saveAndFlush(post);
	//return new ResponseEntity<>(postRepository.save(post),HttpStatus.OK);
	}
	
	///////////////////DELETE METHOD///////////////////////
	@DeleteMapping("/posts/{id}")
	public void SupprimerPost(@PathVariable Long id){
	logger.info("Calling the DELETE Method");
	postRepository.deleteById(id);
	logger.info("DELETE to remove the id post SUCCESS");
	// return new ResponseEntity("Post deleted successfully", HttpStatus.OK);
}
	
	
}
