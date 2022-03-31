package com.mdev.springboot.restControllers.crud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdev.springboot.exception.ApiResourceNotFoundException;
import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Projet;
import com.mdev.springboot.models.Sprint;
import com.mdev.springboot.repository.ProjetRepository;
import com.mdev.springboot.repository.SprintRepository;
import com.mdev.springboot.repository.StoryRepository;
import com.mdev.springboot.repository.TaskRepository;
import com.mdev.springboot.services.ProjectServiceImp;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProjetController {

    @Autowired
    ProjetRepository projetRepository;
    
    @Autowired
    ProjectServiceImp projectServiceImp;
    
    @Autowired
    SprintRepository sprintRepository;
    
    @Autowired
    StoryRepository storyRepository;

    @Autowired
    TaskRepository taskRepository;



    // get all project by Title or All
    //@PreAuthorize("hasRole('PRODUCTOWNER')")
    @GetMapping("/projects")
    public ResponseEntity<List<Projet>> getAllProjects(@RequestParam(required = false) String titre) {
        List<Projet> projets = new ArrayList<Projet>();

        if (titre == null)
            projetRepository.findAll().forEach(projets::add);
        else
            projetRepository.findByTitre(titre).forEach(projets::add);

        if (projets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Comparator<Projet> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getDateDebut().getTime()).compareTo(c2.getDateDebut().getTime());
        };

        Collections.sort(projets, comparator);
        return new ResponseEntity<>(projets, HttpStatus.OK);
    }

    // get project by Id
    //@PreAuthorize("hasRole('PRODUCTOWNER')")
    @GetMapping("/projects/{id}")
    public ResponseEntity<Projet> getProjectById(@PathVariable("id") Long id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with id = " + id));
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }
    
    //// get project by reference
    @RequestMapping(value= "/projects/{pReference}/", method = RequestMethod.GET)
    public ResponseEntity<Projet> getProjectBypReference(@PathVariable("pReference") String pReference){
        
        Projet projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

       
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    // create project
    @PreAuthorize("hasRole('PRODUCTOWNER')")
    @PostMapping("/projects/")
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
        Projet _projet = projetRepository.save(projet);
        return new ResponseEntity<>(_projet, HttpStatus.CREATED);
    }

    // update project by id
    @PutMapping("/projects/{id}")
    public ResponseEntity<Projet> updateProjet(@PathVariable("id") Long id, @RequestBody Projet projetDetails) {
        Projet _projet = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _projet.setTitre(projetDetails.getTitre());
        _projet.setDescriptionProject(projetDetails.getDescriptionProject());
        _projet.setDateDebut(projetDetails.getDateDebut());
        _projet.setDateFin(projetDetails.getDateFin());

        return new ResponseEntity<>(projetRepository.save(_projet), HttpStatus.OK);
    }


    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProjectById(@PathVariable Long id) {

        Projet projet = this.projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id: " + id));
        this.projetRepository.delete(projet);

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/projects/")
    public ResponseEntity<Map<String, Boolean>> deleteAllProjects() throws ApiResourceNotFoundException {

        List<Projet> deletedProject = projetRepository.findAll();
        Map<String, Boolean> response = new HashMap<String, Boolean>();

        response.put("Not found Projects to Delete it!", Boolean.FALSE);
        if (deletedProject.isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        }

        else {
            projetRepository.deleteAll();
            response.put("Projects Deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }

    }
    
    // update projects Sum SP
//    @GetMapping("/projects/sumspbyproject")
//    public ResponseEntity<Map<String, Boolean>> updateTasktable() {
//
//        this.projetRepository.totalSpInProject();
//        Map<String, Boolean> response = new HashMap<String, Boolean>();
//        response.put("Updated sum story points by project", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }
    
    
    //  projects by release brundown chart
    @GetMapping("/projects/releasebdchart")
    public ResponseEntity<Map<String, Boolean>> pReleaseBurndownChart(){
        
        storyRepository.StoryPointUpdate();
        sprintRepository.sprintStoryPointUpdate();
        
        storyRepository.updatePlusSp();
        sprintRepository.updateMoreSp();
        
       
        taskRepository.tasktimeUpdate();
        projetRepository.totalSpInProject();
        
        int sumSp;
        List<Projet> projets = this.projetRepository.findAll();
        
        // Completed table in project for brundown release
        ArrayList<String> spDoneFromSprint = sprintRepository.getListSpCompleted();
        
        System.out.println("******************"+ spDoneFromSprint);

        // More table in project for brundown release
        ArrayList<String> morespFromSprint = sprintRepository.getListMoreSp();
        
        System.out.println("******************"+ morespFromSprint);
               
        for (Projet projet : projets) {
            
            sumSp = projet.getTotalspCommitment();
            
            projet.setpSpwrked(spDoneFromSprint);
            ArrayList<String> arrayspworked = (ArrayList<String>) projet.getpSpwrked();
            projetRepository.projectSpCompletedArray(projet.getId(), arrayspworked);
            
            projet.setpMoresp(morespFromSprint);
            ArrayList<String> arrayspmore = (ArrayList<String>) projet.getpMoresp();
            projetRepository.projectMoreSpArray(projet.getId(), arrayspmore);
            
            
           projet.setpSpCommitment(this.projectServiceImp.releaseBurndownChart(sumSp, spDoneFromSprint, morespFromSprint));
           projetRepository.spCommitmentArray(projet.getId(), projet.getpSpCommitment());
        }
        
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("release Burndown Chart", Boolean.TRUE);
        return ResponseEntity.ok(response);
        
    }
    
    
    // percentage 
    @GetMapping("/projects/percentageSpcChart")
    public ResponseEntity<Map<String, Boolean>> percentageSpcChart(){
        
        List<Projet> projets = this.projetRepository.findAll();
        
        for (Projet projet : projets) {
            
           projet.setPercentage_spc(projectServiceImp.pourcentageStoryPointsCompleted(projet.getTotalspCommitment()));

            
            projetRepository.percentageSpcArray(projet.getId(), projet.getPercentage_spc());
        }
        
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Percentage radio Chart", Boolean.TRUE);
        return ResponseEntity.ok(response);
        
    }


}

