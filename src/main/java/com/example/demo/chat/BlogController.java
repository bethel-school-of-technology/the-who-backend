package com.example.demo.chat;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.chat.Blog;
import com.example.demo.chat.BlogRepository;

@RequestMapping()
@RestController
public class BlogController {

    @Autowired
    BlogRepository dao;

    @GetMapping()
    public List<Blog> getMessages() {
        List<Blog> foundMessages = dao.findAll();
        return foundMessages;
    }
    
    @GetMapping()
    public List<Blog> getJSONMessages() {
    	List<Blog> foundMessages = dao.findAll();
    	return foundMessages;
    	
    }

    @GetMapping()
    public ResponseEntity<Blog> getMessage(@PathVariable(value="id") Integer id) {
        Blog foundMessage = dao.findById(id).orElse(null);

        if(foundMessage == null) {
            return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
        }
        return ResponseEntity.ok(foundMessage);
    }
    
    @PutMapping()
    public ResponseEntity<Blog> putMessage(@PathVariable(value="id") Integer id) {
    	Blog foundMessage = dao.findById(id).orElse(null);
    	
    	if(foundMessage == null) {
    		return ResponseEntity.notFound().header("Message",  "Nothing found with that id").build();
    	}else {
    		dao.save(foundMessage);
    	}
    	return ResponseEntity.ok(foundMessage);
    	
    }
    
    @PostMapping()
    public ResponseEntity<Blog> postMessage(@RequestBody Blog message) {

        // saving to DB using instance of the repo interface
        Blog createdMessage = dao.save(message);

        // RespEntity crafts response to include correct status codes.
        return ResponseEntity.ok(createdMessage);
    }

    @DeleteMapping()
    public ResponseEntity<Blog> deleteMessage(@PathVariable(value="id") Integer id) {
        Blog foundMessage = dao.findById(id).orElse(null);

        if(foundMessage == null) {
            return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
        }else {
            dao.delete(foundMessage);
        }
        return ResponseEntity.ok().build();
    }
}