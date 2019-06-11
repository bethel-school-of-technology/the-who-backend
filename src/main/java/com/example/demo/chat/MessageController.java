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

import com.example.demo.chat.Message;
import com.example.demo.chat.MessageRepository;

@RequestMapping()
@RestController
public class MessageController {

    @Autowired
    MessageRepository dao;

    @GetMapping()
    public List<Message> getMessages() {
        List<Message> foundMessages = dao.findAll();
        return foundMessages;
    }
    
    @GetMapping()
    public List<Message> getJSONMessages() {
    	List<Message> foundMessages = dao.findAll();
    	return foundMessages;
    	
    }

    @GetMapping()
    public ResponseEntity<Message> getMessage(@PathVariable(value="id") Integer id) {
        Message foundMessage = dao.findById(id).orElse(null);

        if(foundMessage == null) {
            return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
        }
        return ResponseEntity.ok(foundMessage);
    }
    
    @PutMapping()
    public ResponseEntity<Message> putMessage(@PathVariable(value="id") Integer id) {
    	Message foundMessage = dao.findById(id).orElse(null);
    	
    	if(foundMessage == null) {
    		return ResponseEntity.notFound().header("Message",  "Nothing found with that id").build();
    	}else {
    		dao.save(foundMessage);
    	}
    	return ResponseEntity.ok(foundMessage);
    	
    }
    
    @PostMapping()
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {

        // saving to DB using instance of the repo interface
        Message createdMessage = dao.save(message);

        // RespEntity crafts response to include correct status codes.
        return ResponseEntity.ok(createdMessage);
    }

    @DeleteMapping()
    public ResponseEntity<Message> deleteMessage(@PathVariable(value="id") Integer id) {
        Message foundMessage = dao.findById(id).orElse(null);

        if(foundMessage == null) {
            return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
        }else {
            dao.delete(foundMessage);
        }
        return ResponseEntity.ok().build();
    }
}