package tn.altercall.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.altercall.exception.ApiResourceNotFoundException;
import tn.altercall.exception.ResourceNotFoundException;
import tn.altercall.entities.Projet;
import tn.altercall.repository.ProjetRepository;
import tn.altercall.repository.SprintRepository;
import tn.altercall.repository.StoryRepository;
import tn.altercall.repository.TaskRepository;
import tn.altercall.services.ProjectServiceImp;
import tn.altercall.utils.DataTaskBugChart;
import tn.altercall.utils.Efficacity;
import tn.altercall.utils.PairArrays;
import tn.altercall.utils.TasksBugs;

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
    // @PreAuthorize("hasRole('PRODUCTOWNER')")
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
    // @PreAuthorize("hasRole('PRODUCTOWNER')")
    @GetMapping("/projects/{id}")
    public ResponseEntity<Projet> getProjectById(@PathVariable("id") Long id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with id = " + id));
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    //// get project by reference
    @RequestMapping(value = "/projects/{pReference}/", method = RequestMethod.GET)
    public ResponseEntity<Projet> getProjectBypReference(@PathVariable("pReference") String pReference) {

        Projet projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    
    // for test 
    @PostMapping("/projectstest/")
    public ResponseEntity<Projet> addProjet(@RequestBody Projet projet) throws ApiResourceNotFoundException {
        Projet saveProjet = projectServiceImp.addProjet(projet);
        return new ResponseEntity<>(saveProjet, HttpStatus.CREATED);
    }
    
    // create project
    //@PreAuthorize("hasRole('PRODUCTOWNER')")
    @PostMapping("/projects/")
    public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
        Projet _projet = projetRepository.save(projet);
        return new ResponseEntity<>(_projet, HttpStatus.CREATED);
    }

    // update project by id
    @PutMapping("/projects/{id}")
    public ResponseEntity<Projet> updateProjet(@PathVariable("id") Long id, @RequestBody Projet projetDetails) {
        Projet _projet = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id = " + id));

        _projet.setTitre(projetDetails.getTitre());
        _projet.setDescriptionProject(projetDetails.getDescriptionProject());
        _projet.setDateDebut(projetDetails.getDateDebut());
        _projet.setTotalstorypointsinitiallycounts(projetDetails.getTotalstorypointsinitiallycounts());
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

    //Delete all project
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

    
    // projects by release brundown chart
//    @GetMapping("/projects/updateallsp")
//    public ResponseEntity<Map<String, Boolean>> updatetotalSpInProject() {
//
//        this.storyRepository.StoryPointUpdate();
//        this.sprintRepository.sprintStoryPointUpdate();
//
//
//        storyRepository.updatePlusSp();
//        sprintRepository.updateMoreSp();
//
//        taskRepository.tasktimeUpdate();
//        projetRepository.totalSpInProject();
//
//        Map<String, Boolean> response = new HashMap<String, Boolean>();
//        response.put("story points updated", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//
//    }
    // Projects by release brundown chart
    @GetMapping("/projects/releasebdchart/{pReference}")
    public ResponseEntity<Map<String, Boolean>> pReleaseBurndownChart(@PathVariable("pReference") String pReference) {

        storyRepository.StoryPointUpdate();
        sprintRepository.sprintStoryPointUpdate();

        storyRepository.updatePlusSp();
        sprintRepository.updateMoreSp();

        taskRepository.tasktimeUpdate();
        projetRepository.totalSpInProject();

        Projet projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        // Completed SP table in project for release brundown 
        ArrayList<String> spDoneFromSprint = sprintRepository.getListSpCompleted(pReference);

        // System.out.println("********spDoneFromSprint*********" + spDoneFromSprint);

        // More SP table in project for release brundown 
        ArrayList<String> morespFromSprint = sprintRepository.getListMoreSp(pReference);

        // System.out.println("*******morespFromSprint**********" + morespFromSprint);

        // for (Projet projet : projets) {

        int sumSp = projet.getTotalstorypointsinitiallycounts();

        projet.setpSpwrked(spDoneFromSprint);
        ArrayList<String> arrayspworked = (ArrayList<String>) projet.getpSpwrked();
        projetRepository.projectSpCompletedArray(projet.getId(), arrayspworked);

        projet.setpMoresp(morespFromSprint);
        ArrayList<String> arrayspmore = (ArrayList<String>) projet.getpMoresp();
        projetRepository.projectMoreSpArray(projet.getId(), arrayspmore);

        projet.setpSpCommitment(this.projectServiceImp.releaseBurndownChart(sumSp, spDoneFromSprint, morespFromSprint));
        projetRepository.spCommitmentArray(projet.getId(), projet.getpSpCommitment());
        // }

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("release Burndown Chart", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

    // percentage SP by project ** story Points Completed Chart
    @GetMapping("/projects/percentageSpcChart/{pReference}")
    public ResponseEntity<Map<String, Boolean>> percentageSpcChart(@PathVariable("pReference") String pReference) {

        storyRepository.StoryPointUpdate();
        sprintRepository.sprintStoryPointUpdate();
        ArrayList<String> tabFromdb = sprintRepository.getListSpCompleted(pReference);

        Projet projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        projet.setPercentage_spc(projectServiceImp
                .pourcentageStoryPointsCompleted(projet.getTotalstorypointsinitiallycounts(), tabFromdb));

        projetRepository.percentageSpcArray(projet.getId(), projet.getPercentage_spc());

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Percentage radio Chart", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

    // task status percentage chart by project  preference
    @GetMapping("/projects/percentTaskStatuscChart/{pReference}")
    public ResponseEntity<PairArrays> getListtaskByStatus(@PathVariable("pReference") String pReference) {

        Projet projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        PairArrays pair = projectServiceImp.listTaskByStatus(projet.getpReference());
        return new ResponseEntity<>(pair, HttpStatus.OK);

    }

    // select date_debut in task by project reference
    @GetMapping("/projects/listStartDateTask/{pReference}")
    public ResponseEntity<ArrayList<String>> getListTaskStartDateBypRef(@PathVariable("pReference") String pReference) {

        Projet projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        ArrayList<String> listStartDate = projetRepository.getListTaskStartDate(pReference);
        return new ResponseEntity<>(listStartDate, HttpStatus.OK);

    }

    // get efficacity by startDate of task by project ref
    @RequestMapping(value = "/projects/getEfficacity/{pReference}", method = RequestMethod.PUT)
    public ResponseEntity<PairArrays> getEfficacityByStartDateTask(@PathVariable("pReference") String pReference,
            @RequestBody ArrayList<Efficacity> efficacityDataRequest) throws ParseException {

        Map<Date, Date> mapDate = new HashMap<Date, Date>();

        for (Efficacity efficacity : efficacityDataRequest) {
            mapDate.put(efficacity.getStartDate(), efficacity.getEndDate());
        }
        // System.out.println(mapDate);
        PairArrays dataEffecacityChart = this.projectServiceImp.efficacityByStartDateTask(pReference, mapDate);
        return new ResponseEntity<>(dataEffecacityChart, HttpStatus.OK);
    }

    // select work completed percentage demi-cercle in task by project reference
    @GetMapping("/projects/percentageStoryPointsInProject/{pReference}")
    public ResponseEntity<Map<String, String>> getpercentageStoryPointsInProject( @PathVariable("pReference") String pReference) {

        Projet projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));
        // calcul % completed sp
        float percentageSpCompletedByProject = ((float) projet.getTotalspCompleted() * 100)
                / (float) projet.getTotalstorypointsinitiallycounts();

        // calcul % commitment sp
        float percentageSpCommitmentByProject = ((float) projet.getTotalspCommitment() * 100)
                / (float) projet.getTotalstorypointsinitiallycounts();

        String percentageSpCompletedByProjectString = String.format("%.2f", percentageSpCompletedByProject);
        String percentageSpCommitmentByProjectString = String.format("%.2f", percentageSpCommitmentByProject);
        
        // total sp completed
        String totalspcompleted =  String.valueOf(projet.getTotalspCompleted());
        
        // total sp initialized
        String totalstorypointsinitiallycounts =  String.valueOf(projet.getTotalstorypointsinitiallycounts());
        
        Map<String, String> data = new HashMap<String, String>();
        
        data.put("SpCompleted", percentageSpCompletedByProjectString);
        data.put("SpCommitment", percentageSpCommitmentByProjectString);
        data.put("totalspcompleted", totalspcompleted);
        data.put("totalstorypointsinitiallycounts", totalstorypointsinitiallycounts);
        //System.out.println(data);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
    
    // get taskBugs by startDate of task by project ref
    @RequestMapping(value = "/projects/gettaskbugs/{pReference}", method = RequestMethod.PUT)
    public ResponseEntity<DataTaskBugChart> getTaskBugByStartDateTask(@PathVariable("pReference") String pReference,
            @RequestBody ArrayList<TasksBugs> dataRequest)  {

        Map<Date, Date> mapDate = new HashMap<Date, Date>();

        for (TasksBugs tasksBugs : dataRequest) {
            mapDate.put(tasksBugs.getStartDate(), tasksBugs.getEndDate());
        }
        // System.out.println(mapDate);
        DataTaskBugChart dataChart = this.projectServiceImp.getTasksBugs(pReference, mapDate);
        return new ResponseEntity<>(dataChart, HttpStatus.OK);
    }

   
    
    

}
