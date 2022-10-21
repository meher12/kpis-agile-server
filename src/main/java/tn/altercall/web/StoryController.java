package tn.altercall.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.altercall.entities.Sprint;
import tn.altercall.entities.Story;
import tn.altercall.exception.ResourceNotFoundException;
import tn.altercall.repository.SprintRepository;
import tn.altercall.repository.StoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class StoryController {

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    StoryRepository storyRepository;

    // search by sprint reference
    @GetMapping("/searchBySReference")
    public ResponseEntity<List<Story>> getAllStoryBySpReference(@RequestParam(required = false) String sprintReference) {
       // try {
            var sprint = new Sprint();
            List<Story> stories = new ArrayList<>();

            if (sprintReference == null) {
                storyRepository.findAll().forEach(stories::add);
            }
            else {
                sprint = sprintRepository.findBysReference(sprintReference)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Sprint with reference: " + sprintReference));

                storyRepository.findBySprintId(sprint.getId()).forEach(stories::add);
            }

            if (stories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Comparator<Story> comparator = (c1, c2) -> Long.valueOf(c1.getPriority()).compareTo(c2.getPriority());

            Collections.sort(stories, comparator);

            return new ResponseEntity<>(stories, HttpStatus.OK);
       /* } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
    }

    // get All Story By SprinttId
    @RequestMapping(value = "/sprints/{sprint_id}/stories")
    ResponseEntity<List<Story>> getAllStroyBySprintId(@PathVariable("sprint_id") Long sprint_id) {
        if (!sprintRepository.existsById(sprint_id)) {
            throw new ResourceNotFoundException("Not found Sprint with id = " + sprint_id);
        }
        List<Story> stories = storyRepository.findBySprintId(sprint_id);

        Comparator<Story> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getPriority()).compareTo(c2.getPriority());
        };

        Collections.sort(stories, comparator);

        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    // get all story
    @RequestMapping(value = "/stories", method = RequestMethod.GET)
    public ResponseEntity<List<Story>> getAllStory() {

        List<Story> stories = storyRepository.findAll();

        Comparator<Story> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getPriority()).compareTo(c2.getPriority());
        };

        Collections.sort(stories, comparator);

        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    // get All Story By Sprint Reference
    @RequestMapping(value = "/{sReference}/stories", method = RequestMethod.GET)
    public ResponseEntity<List<Story>> getAllStoryBySprintReference(
            @PathVariable(value = "sReference") String sReference) {
        Sprint sprint = sprintRepository.findBysReference(sReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Sprint with Reference : " + sReference));
        List<Story> stories = storyRepository.findBySprintId(sprint.getId());

        Comparator<Story> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getPriority()).compareTo(c2.getPriority());
        };

        Collections.sort(stories, comparator);
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    // get Story by id
    @GetMapping(value = "/stories/story/{id}")
    public ResponseEntity<Story> getStoryById(@PathVariable(value = "id") Long id) {

        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Story with id = " + id));
        return new ResponseEntity<>(story, HttpStatus.OK);
    }

    //// get story by reference
    @RequestMapping(value = "/stories/{stReference}", method = RequestMethod.GET)
    public ResponseEntity<Story> getStoryBystReference(@PathVariable("stReference") String stReference) {

        Story story = storyRepository.findBystReference(stReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Story with Reference : " + stReference));

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
    @PutMapping("/story/{id}")
    public ResponseEntity<Story> updateStory(@PathVariable("id") Long id, @RequestBody Story storyRequest) {
        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Story Id " + id + "not found"));

        story.setStname(storyRequest.getStname());
        story.setStReference(storyRequest.getStReference());
        story.setStdescription(storyRequest.getStdescription());
        // story.setSpCompleted(storyRequest.getSpCompleted());
        // story.setStoryPoint(storyRequest.getStoryPoint());
        story.setPriority(storyRequest.getPriority());

        Story updatedStory = storyRepository.save(story);

        return new ResponseEntity<>(updatedStory, HttpStatus.OK);
    }

    // delete Story By id
    @DeleteMapping("/story/{id}")
    public ResponseEntity<HttpStatus> deleteStory(@PathVariable("id") long id) {
        storyRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // delete All Story Of Sprint
    @DeleteMapping("/sprints/{sprint_id}/stories")
    public ResponseEntity<List<Story>> deleteAllStoryOfSprint(@PathVariable(value = "sprint_id") Long sprint_id) {
        if (!sprintRepository.existsById(sprint_id)) {
            throw new ResourceNotFoundException("Not found Sprint with id = " + sprint_id);
        }

        storyRepository.deleteAllBySprintId(sprint_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // update story points in sprint
//    @GetMapping("/storie/updatestorypoints")
//    public ResponseEntity<Map<String, Boolean>> updateStoryPoint() {
//
//        this. storyRepository.StoryPointUpdate();
//        Map<String, Boolean> response = new HashMap<String, Boolean>();
//        response.put("Updated story points", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }

    // update Plus story points
//    @GetMapping("/storie/updatePlussp")
//    public ResponseEntity<Map<String, Boolean>> updatePlusSp() {
//
//        this. storyRepository.updatePlusSp();
//        Map<String, Boolean> response = new HashMap<String, Boolean>();
//        response.put("Updated story points", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }


}
