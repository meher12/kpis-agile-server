package com.mdev.springboot.restControllers.projet;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Projet;
import com.mdev.springboot.repository.ProjetRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/projects")
public class ProjetController {

    @Autowired
    ProjetRepository projetRepository;

    // get all project by Title or All
    @GetMapping()
    public ResponseEntity<List<Projet>> getAllProjects(@RequestParam(required = false) String titre) {
        List<Projet> projets = new ArrayList<Projet>();

        if (titre == null)
            projetRepository.findAll().forEach(projets::add);
        else
            projetRepository.findByTitre(titre).forEach(projets::add);

        if (projets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(projets, HttpStatus.OK);
    }

    // get project by Id
    @GetMapping("/{id}")
    public ResponseEntity<Projet> getProjectById(@PathVariable("id") Long id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with id = " + id));
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    // create project
    @PreAuthorize("hasRole('PRODUCTOWNER')")
    @PostMapping()
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
        Projet _projet = projetRepository.save(projet);
        return new ResponseEntity<>(_projet, HttpStatus.CREATED);
    }

    // update project by id
    @PutMapping("/{id}")
    public ResponseEntity<Projet> updateProjet(@PathVariable("id") long id, @RequestBody Projet projetDetails) {
        Projet _projet = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _projet.setTitre(projetDetails.getTitre());
        _projet.setDescriptionProject(projetDetails.getDescriptionProject());
        _projet.setDateDebut(projetDetails.getDateDebut());
        _projet.setDateFin(projetDetails.getDateFin());

        return new ResponseEntity<>(projetRepository.save(_projet), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProjet(@PathVariable("id") Long id) {
        projetRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllProjects() {
        projetRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
