package com.mdev.springboot.restControllers.sprint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Sprint;
import com.mdev.springboot.repository.ProjetRepository;
import com.mdev.springboot.repository.SprintRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/sprints")
public class SprintController {

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    ProjetRepository projetRepository;

    // get All Sprints By ProjectId
    @GetMapping("/projects/{projectId}/sprints")
    public ResponseEntity<List<Sprint>> getAllSprintsByProjectId(@PathVariable(value = "projetId") Long projetId) {
        if (!projetRepository.existsById(projetId)) {
            throw new ResourceNotFoundException("Not found Project with id = " + projetId);
        }

        List<Sprint> sprints = sprintRepository.findByProjetId(projetId);
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    // get Sprints By ProjectId
    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getSprintsByProjectId(@PathVariable(value = "id") Long id) {
        Sprint sprints = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Sprints with id = " + id));

        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    // create Sprint
    @PostMapping("/projects/{projetId}/sprints")
    public ResponseEntity<Sprint> createSprint(@PathVariable(value = "projetId") Long projetId,
            @RequestBody Sprint sprintRequest) {
        Sprint sprint = projetRepository.findById(projetId).map(project -> {
            sprintRequest.setProjetId(project);
            return sprintRepository.save(sprintRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Project with id = " + projetId));

        return new ResponseEntity<>(sprint, HttpStatus.CREATED);
    }

    // update Sprint
    @PutMapping("/{id}")
    public ResponseEntity<Sprint> updateSprint(@PathVariable("id") Long id, @RequestBody Sprint sprintRequest) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SprintId " + id + "not found"));

        sprint.setStitre(sprintRequest.getStitre());
        sprint.setSdescription(sprintRequest.getSdescription());
        sprint.setSdateDebut(sprintRequest.getSdateDebut());
        sprint.setSdateFin(sprintRequest.getSdateFin());
        sprint.setsUniqueID(sprintRequest.getsUniqueID());

        return new ResponseEntity<>(sprintRepository.save(sprint), HttpStatus.OK);
    }

    // delete Sprint By id
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSprint(@PathVariable("id") long id) {
        sprintRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // delete All Sprints Of Project
    @DeleteMapping("/projects/{projectId}/sprints")
    public ResponseEntity<List<Sprint>> deleteAllSprintsOfProject(@PathVariable(value = "projetId") Long projetId) {
        if (!projetRepository.existsById(projetId)) {
            throw new ResourceNotFoundException("Not found Project with id = " + projetId);
        }

        sprintRepository.deleteByProjetId(projetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}