package com.mdev.springboot.restControllers.sprint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Sprint;
import com.mdev.springboot.repository.SprintRepository;

@RestController
@RequestMapping("api/v1/sprint")
public class SprintController {

    @Autowired
    SprintRepository sprintRepository;

    // get all sprints
    @RequestMapping(value = "/sprints", method = RequestMethod.GET)
    List<Sprint> getAllSprint() {
        return this.sprintRepository.findAll();
    }

    // get sprint by id rest api
    @RequestMapping(value = "/sprints/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sprint> getSprintById(@PathVariable Long id) {
        Sprint sprint = this.sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found for this id: " + id));
        return ResponseEntity.ok(sprint);

    }

    // create sprint rest api
    @RequestMapping(value = "/sprints", method = RequestMethod.POST)
    public Sprint createSprint(@RequestBody Sprint sprint) {
        return this.sprintRepository.save(sprint);
    }

    // update sprint by id rest api
    @RequestMapping(value = "/sprints/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sprint> updateSprint(@PathVariable Long id, @RequestBody Sprint sprintDetails) {
        Sprint sprint = this.sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found for this id :" + id));

        sprint.setStitre(sprintDetails.getStitre());
        sprint.setSdescription(sprintDetails.getSdescription());
        sprint.setSdate_debut(sprintDetails.getSdate_debut());
        sprint.setSdate_fin(sprintDetails.getSdate_fin());
        sprint.setStory(sprintDetails.getStory());
        sprint.setSprint_of_project(sprintDetails.getSprint_of_project());

        Sprint updatedSprint = this.sprintRepository.save(sprint);
        return ResponseEntity.ok(updatedSprint);
    }

    // update sprint by id rest api
    @RequestMapping(value = "/sprints/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteSprint(@PathVariable Long id) {

        Sprint sprint = this.sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found for this id" + id));

        this.sprintRepository.delete(sprint);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}