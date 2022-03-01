package com.mdev.springboot.restControllers.projet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Projet;
import com.mdev.springboot.repository.ProjetRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/projet")
public class ProjetController {

    @Autowired
    ProjetRepository projetRepository;

    // get project list
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public List<Projet> getProjetList() {
        return this.projetRepository.findAll();
    }

    // get project by id
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.GET)
    public ResponseEntity<Projet> getProjetById(@PathVariable Long id) {
        Projet projet = this.projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id: " + id));
        return ResponseEntity.ok(projet);
    }

    // create projet
    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public Projet createProjet(@RequestBody Projet projet) {
        return this.projetRepository.save(projet);
    }

    // update project by id
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Projet> updateProjectById(@PathVariable Long id, @RequestBody Projet projetDetails) {

        Projet projet = this.projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id: " + id));

        projet.setTitre(projetDetails.getTitre());
        projet.setDescription(projetDetails.getDescription());
        projet.setDate_debut(projetDetails.getDate_debut());
        projet.setDate_fin(projetDetails.getDate_fin());
        projet.setIteration_sprint(projetDetails.getIteration_sprint());
        projet.setSprints(projetDetails.getSprints());

        Projet updatedProjet = this.projetRepository.save(projet);
        return ResponseEntity.ok(updatedProjet);
    }

    // delete Projet by id
    @RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteProjectById(@PathVariable Long id) {

        Projet projet = this.projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id: " + id));
        this.projetRepository.delete(projet);
        
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}
