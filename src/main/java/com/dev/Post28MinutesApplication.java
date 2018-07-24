package com.dev;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dev.entity.Post;
import com.dev.repository.PostRepository;

@SpringBootApplication
public class Post28MinutesApplication implements CommandLineRunner{

    @Autowired
    private PostRepository postRepository;
    
//    User u1 =new User("admin","admin",18);
//    User u2 =new User("user","user",15);
    
	public static void main(String[] args) {
		SpringApplication.run(Post28MinutesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		postRepository.save(new Post("Anas","Developpeur Front-End",new Date()));
		postRepository.save(new Post("Jamal","Developpeur Web",new Date()));
		//postRepository.save(new Post("Ali","Developpeur Back-End",new Date()));
	}
}
