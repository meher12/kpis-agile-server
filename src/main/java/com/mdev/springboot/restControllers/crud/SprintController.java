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

import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Projet;
import com.mdev.springboot.models.Sprint;
import com.mdev.springboot.repository.ProjetRepository;
import com.mdev.springboot.repository.SprintRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SprintController {

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    ProjetRepository projetRepository;

    // get All Sprints By ProjectId
    @GetMapping("/sprints/projects/{projet_id}/sprints")
    public ResponseEntity<List<Sprint>> getAllSprintsByProjectId(@PathVariable("projet_id") Long projet_id) {

        if (!projetRepository.existsById(projet_id)) {
            throw new ResourceNotFoundException("Not found Project with id = " + projet_id);
        }

        List<Sprint> sprints = sprintRepository.findByProjetId(projet_id);
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    // get All Sprint By Projet Reference
    @RequestMapping(value = "/sprints/{pReference}/sprints", method = RequestMethod.GET)
    public ResponseEntity<List<Sprint>> getAllSprintByProjetReference(@PathVariable( value ="pReference") String pReference) {

        Projet projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        List<Sprint> sprints = sprintRepository.findByProjetId(projet.getId());
        return new ResponseEntity<>(sprints, HttpStatus.OK);

    }

    // get All Sprints
    @GetMapping("/sprints/sprints")
    public ResponseEntity<List<Sprint>> getAllSprints() {
        List<Sprint> sprints = sprintRepository.findAll();
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    // get Sprints By Id
    @GetMapping("/sprints/sprints/{id}")
    public ResponseEntity<Sprint> getSprintsById(@PathVariable(value = "id") Long id) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Sprint with id = " + id));

        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    // create Sprint
    @PostMapping("/sprints/projects/{projet_id}/sprints")
    public ResponseEntity<Sprint> createSprint(@PathVariable(value = "projet_id") Long projet_id,
            @RequestBody Sprint sprintRequest) {
        Sprint sprint = projetRepository.findById(projet_id).map(project -> {
            sprintRequest.setProjet(project);
            return sprintRepository.save(sprintRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Project with id = " + projet_id));

        return new ResponseEntity<>(sprint, HttpStatus.CREATED);
    }

    // update Sprint
    @RequestMapping(value = "/sprints/sprints/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sprint> updateSprint(@PathVariable("id") Long id, @RequestBody Sprint sprintRequest) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SprintId " + id + "not found"));

        sprint.setStitre(sprintRequest.getStitre());
        sprint.setSdescription(sprintRequest.getSdescription());
        sprint.setSdateDebut(sprintRequest.getSdateDebut());
        sprint.setSdateFin(sprintRequest.getSdateFin());

        // sprint.setProjet(sprintRequest.getProjet());

        return new ResponseEntity<>(sprintRepository.save(sprint), HttpStatus.OK);
    }

    // delete Sprint By id
    @DeleteMapping("/sprints/sprints/{id}")
    public ResponseEntity<HttpStatus> deleteSprint(@PathVariable("id") long id) {
        sprintRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // delete All Sprints Of Project
    @DeleteMapping("/sprints/projects/{projet_id}/sprints")
    public ResponseEntity<List<Sprint>> deleteAllSprintsOfProject(@PathVariable(value = "projet_id") Long projet_id) {
        if (!projetRepository.existsById(projet_id)) {
            throw new ResourceNotFoundException("Not found Project with id = " + projet_id);
        }

        sprintRepository.deleteByProjetId(projet_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("/instructors/{instructorId}/courses/{courseId}")
//    public ResponseEntity < ? > deleteCourse(@PathVariable(value = "instructorId") Long instructorId,
//        @PathVariable(value = "courseId") Long courseId) throws ResourceNotFoundException {
//        return courseRepository.findByIdAndInstructorId(courseId, instructorId).map(course - > {
//            courseRepository.delete(course);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException(
//            "Course not found with id " + courseId + " and instructorId " + instructorId));
//    }

}
