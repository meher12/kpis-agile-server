package com.mdev.springboot.restControllers.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mdev.springboot.exception.ApiResourceNotFoundException;
import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Sprint;
import com.mdev.springboot.models.Story;
import com.mdev.springboot.repository.SprintRepository;
import com.mdev.springboot.repository.StoryRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class StoryController {

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    StoryRepository storyRepository;

    // get All Story By SprinttId
    @RequestMapping(value = "/sprints/{sprint_id}/stories")
    ResponseEntity<List<Story>> getAllStroyBySprintId(@PathVariable("sprint_id") Long sprint_id) {
        if (!sprintRepository.existsById(sprint_id)) {
            throw new ResourceNotFoundException("Not found Sprint with id = " + sprint_id);
        }
        List<Story> stories = storyRepository.findBySprintId(sprint_id);
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    // get all story
    @RequestMapping(value = "/stories", method = RequestMethod.GET)
    public ResponseEntity<List<Story>> getAllStory() {
        return new ResponseEntity<>(storyRepository.findAll(), HttpStatus.OK);
    }

    // get All Story By Sprint Reference
    @RequestMapping(value = "/{sReference}/stories", method = RequestMethod.GET)
    public ResponseEntity<List<Story>> getAllStoryBySprintReference(
            @PathVariable(value = "sReference") String sReference) {
        Sprint sprint = sprintRepository.findBysReference(sReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Sprint with Reference : " + sReference));
        List<Story> stories = storyRepository.findBySprintId(sprint.getId());
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    // get Story by id
    @GetMapping("/story/{id}")
    public ResponseEntity<Story> getStoryById(@PathVariable("id") Long id) {

        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new ApiResourceNotFoundException("Not found Story with id = " + id));
        return new ResponseEntity<>(story, HttpStatus.OK);
    }

    // create Story
    @PostMapping("/sprints/{sprint_id}/story")
    public ResponseEntity<Story> createStory(@PathVariable(value = "sprint_id") Long sprint_id,
            @RequestBody Story storyRequest) {
        Story story = sprintRepository.findById(sprint_id).map(sprint -> {
            storyRequest.setSprint(sprint);
            return storyRepository.save(storyRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Sprint with id = " + sprint_id));

        return new ResponseEntity<>(story, HttpStatus.CREATED);
    }

    // update Story
    @RequestMapping(value = "/story/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Story> updateStory(@PathVariable("id") Long id, @RequestBody Story storyRequest) {
        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Story Id " + id + "not found"));

        story.setStname(storyRequest.getStname());
        story.setStdescription(storyRequest.getStdescription());
        story.setPriorite(storyRequest.getPriorite());
        story.setStoryPoint(storyRequest.getStoryPoint());

        return new ResponseEntity<>(storyRepository.save(story), HttpStatus.OK);
    }

    // delete Story By id
    @DeleteMapping("/story/{id}")
    public ResponseEntity<HttpStatus> deleteStory(@PathVariable("id") long id) {
        storyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // delete All Story Of Sprint
    @DeleteMapping("/sprints/{sprint_id}/story")
    public ResponseEntity<List<Story>> deleteAllStoryOfSprint(@PathVariable(value = "sprint_id") Long sprint_id) {
        if (!sprintRepository.existsById(sprint_id)) {
            throw new ResourceNotFoundException("Not found Sprint with id = " + sprint_id);
        }

        storyRepository.deleteAllBySprintId(sprint_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
