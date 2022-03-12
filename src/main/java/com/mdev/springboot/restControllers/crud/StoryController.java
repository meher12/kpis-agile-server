package com.mdev.springboot.restControllers.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Story;
import com.mdev.springboot.repository.SprintRepository;
import com.mdev.springboot.repository.StoryRepository;

@RestController
@CrossOrigin(origins = "*", maxAge=3600)
@RequestMapping("/api/stories")
public class StoryController {
    
    @Autowired
    SprintRepository sprintRepository;
    
    @Autowired
    StoryRepository storyRepository;
    
    // get All Story By SprinttId
    @RequestMapping(value="/sprints/{sprint_id}/stories")
    ResponseEntity<List<Story>> getAllStroyBySprintId(@PathVariable("sprint_id") Long sprint_id){
        
        if(!sprintRepository.existsById(sprint_id)) {
            throw new ResourceNotFoundException("Not found Sprint with id = " + sprint_id);
        }
        
        List <Story> stories = storyRepository.findBySprintId(sprint_id);
        return new ResponseEntity<>(stories, HttpStatus.OK);
        
    }
}
