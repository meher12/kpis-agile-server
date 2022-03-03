package com.mdev.springboot.restControllers.projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mdev.springboot.exception.ApiResourceNotFoundException;
import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Projet;
import com.mdev.springboot.repository.ProjetRepository;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("api/projects")
public class ProjetController {

    @Autowired
    ProjetRepository projetRepository;

    // get project list
    // @PreAuthorize("hasRole('PRODUCTOWNER')")
    // @RequestMapping(value = "/projects", method = RequestMethod.GET)
//    public ResponseEntity<?> getProjetList() {
//
//        List<Projet> projets = new ArrayList<>();
//        projets = this.projetRepository.findAll();
//
//       if ((AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).contains("ROLE_PRODUCTOWNER"))) {
//            return ResponseEntity.ok(projets);
//        } else {
//            return ResponseEntity.badRequest().body(new MessageResponse("Sorry, you are not allowed to access this content!"));
//        }
//    }

    // get project list
    @PreAuthorize("hasRole('PRODUCTOWNER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getProjetList() throws ApiResourceNotFoundException {

        List<Projet> projets = new ArrayList<>();

        projets = this.projetRepository.findAll();
        if (projets.isEmpty()) {
            throw new ApiResourceNotFoundException("There are no projects in the database ");
        }
        return ResponseEntity.ok(projets);
    }

    // get project by id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Projet> getProjetById(@PathVariable Long id) {
        Projet projet = this.projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id: " + id));
        return ResponseEntity.ok(projet);
    }

    // create projet
    @PreAuthorize("hasRole('PRODUCTOWNER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) throws ApiResourceNotFoundException{
        Projet projetSaved =  this.projetRepository.save(projet);
        if (  projetSaved == null) {
            throw new ApiResourceNotFoundException("Project is not saved ");
        }
        return ResponseEntity.ok(projetSaved);
    }
    
    //@PreAuthorize("hasRole('PRODUCTOWNER')")
//    @RequestMapping(value = "/projects", method = RequestMethod.POST)
//    public Projet createProjet(@RequestBody Projet projet) {
//        return this.projetRepository.save(projet);     
//       // return ResponseEntity.ok(projetSaved);
//    }

    // update project by id
    @PreAuthorize("hasRole('PRODUCTOWNER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Projet> updateProjectById(@PathVariable Long id, @RequestBody Projet projetDetails) {

        Projet projet = this.projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id: " + id));

        projet.setTitre(projetDetails.getTitre());
        projet.setDescription(projetDetails.getDescription());
        projet.setDate_debut(projetDetails.getDate_debut());
        projet.setDate_fin(projetDetails.getDate_fin());
        // projet.setSprints(projetDetails.getSprints());

        Projet updatedProjet = this.projetRepository.save(projet);
        return ResponseEntity.ok(updatedProjet);
    }

    // delete Projet by id
    @PreAuthorize("hasRole('PRODUCTOWNER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteProjectById(@PathVariable Long id){

        Projet projet = this.projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id: " + id));
        this.projetRepository.delete(projet);

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

}
