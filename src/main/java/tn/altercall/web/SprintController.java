package tn.altercall.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.altercall.entities.Project;
import tn.altercall.entities.Sprint;
import tn.altercall.exception.ResourceNotFoundException;
import tn.altercall.repository.ProjetRepository;
import tn.altercall.repository.SprintRepository;
import tn.altercall.repository.StoryRepository;
import tn.altercall.repository.TaskRepository;
import tn.altercall.services.SprintServiceImp;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class SprintController {

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    SprintServiceImp sprintServiceImp;

    @Autowired
    ProjetRepository projetRepository;

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    TaskRepository taskRepository;


    // search by project reference
    @GetMapping("/projects/searchByPReference")
    public ResponseEntity<List<Sprint>> getAllSprintsByPReference(@RequestParam(required = false) String projectReference) {
        // try {
        var project = new Project();
        List<Sprint> sprints = new ArrayList<>();

        if (projectReference == null)
            sprintRepository.findAll().forEach(sprints::add);
        else
            project = projetRepository.findBypReference(projectReference)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Project with reference: " + projectReference));

        sprintRepository.findByProjetId(project.getId()).forEach(sprints::add);

        if (sprints.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Comparator<Sprint> comparator = (c1, c2) -> Long.valueOf(c1.getSdateDebut().getTime()).compareTo(c2.getSdateDebut().getTime());

        Collections.sort(sprints, comparator);
        return new ResponseEntity<>(sprints, HttpStatus.OK);
       /* } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
    }

    // get All Sprints By ProjectId
    @GetMapping("/sprints/projects/{projet_id}/sprints")
    public ResponseEntity<List<Sprint>> getAllSprintsByProjectId(@PathVariable("projet_id") Long projet_id) {

        if (!projetRepository.existsById(projet_id)) {
            throw new ResourceNotFoundException("Not found Project with id = " + projet_id);
        }

        List<Sprint> sprints = sprintRepository.findByProjetId(projet_id);

        Comparator<Sprint> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getSdateDebut().getTime()).compareTo(c2.getSdateDebut().getTime());
        };

        Collections.sort(sprints, comparator);
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    // get All Sprint By Project Reference
    @RequestMapping(value = "/sprints/{pReference}/sprints", method = RequestMethod.GET)
    public ResponseEntity<List<Sprint>> getAllSprintByProjetReference(
            @PathVariable(value = "pReference") String pReference) {

        Project projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        List<Sprint> sprints = sprintRepository.findByProjetId(projet.getId());

        Comparator<Sprint> comparatorSprint = (c1, c2) -> Long.valueOf(c1.getSdateDebut().getTime()).compareTo(c2.getSdateDebut().getTime());

        Collections.sort(sprints, comparatorSprint);

        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    // get All Sprints
    @GetMapping("/sprints/sprints")
    public ResponseEntity<List<Sprint>> getAllSprints() {
        List<Sprint> sprints = sprintRepository.findAll();

        Comparator<Sprint> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getSdateDebut().getTime()).compareTo(c2.getSdateFin().getTime());
        };

        Collections.sort(sprints, comparator);
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    // get Sprints By Id
    @GetMapping("/sprints/sprints/{id}")
    public ResponseEntity<Sprint> getSprintsById(@PathVariable(value = "id") Long id) {
        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Sprint with id = " + id));

        return new ResponseEntity<>(sprint, HttpStatus.OK);
    }

    // get sprint by reference
    @RequestMapping(value = "/sprints/{sReference}", method = RequestMethod.GET)
    public ResponseEntity<Sprint> getSprintBypReference(@PathVariable("sReference") String sReference) {

        Sprint sprint = sprintRepository.findBysReference(sReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Sprint with Reference : " + sReference));

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

        sprintRepository.deleteAllByProjetId(projet_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // update work Commitment and work Completed in sprint
    @GetMapping("/sprints/updatesp")
    public ResponseEntity<Map<String, Boolean>> updateStoryPointInSprint() {

        this.storyRepository.StoryPointUpdate();
        this.sprintRepository.sprintStoryPointUpdate();


        storyRepository.updatePlusSp();
        sprintRepository.updateMoreSp();

        taskRepository.tasktimeUpdate();
        projetRepository.totalSpInProject();


        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Updated", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // get number of days in sprint
    @RequestMapping(value = "/sprints/daysbrundownChart", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Boolean>> daysInSprint() throws Exception {

        this.storyRepository.StoryPointUpdate();
        this.sprintRepository.sprintStoryPointUpdate();

        List<Sprint> sprints = sprintRepository.findAll();
        for (Sprint sprint : sprints) {
            sprint.setDaysarray(sprintServiceImp.numberOfDaysInSprint(sprint.getSdateDebut(), sprint.getSdateFin()));
            List<String> resultArray = sprint.getDaysarray();
            sprintRepository.sprintArrayDays(sprint.getId(), resultArray);
        }

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Days Inserted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // get idealLine for sprint story points
    @RequestMapping(value = "/sprints/ideallbrundownChart", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Boolean>> idealLineOfSprint() {

        this.storyRepository.StoryPointUpdate();
        this.sprintRepository.sprintStoryPointUpdate();

        List<Sprint> sprints = sprintRepository.findAll();
        int SpTotalForIdealLine = 0;
        for (Sprint sprint : sprints) {

            // Insert in db
            SpTotalForIdealLine = sprint.getWorkCompleted() + sprint.getWorkCommitment();
            sprint.setIdealLinearray(sprintServiceImp.getIdealLine(sprint.getSdateDebut(), sprint.getSdateFin(), SpTotalForIdealLine));
            List<String> arrayLine = sprint.getIdealLinearray();
            sprintRepository.sprintArrayOfIdealLine(sprint.getId(), arrayLine);
            // log.info("ideal line {} ",arrayLine);
        }

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Inserted ideal line", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // update sprint work completed for brundown chart
    @RequestMapping(value = "/sprints/addspCompleted/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Map<String, Boolean>> updateSprintWorkCompleted(@PathVariable("id") Long id,
                                                                          @RequestBody ArrayList<Object> storiesRequest) {

        this.storyRepository.StoryPointUpdate();
        this.sprintRepository.sprintStoryPointUpdate();

        Sprint sprint = sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SprintId " + id + "not found"));

        //System.out.println("--- ***************************** ***********************" + storiesRequest);

        ArrayList<String> arrayJson = new ArrayList<String>();
        ArrayList<String> arrayFiltred = new ArrayList<String>();

        storiesRequest.forEach(name -> {
            arrayJson.add(name.toString());
        });

        for (String item : arrayJson) {
            String number = item.replaceAll("[^0-9]", "");
            arrayFiltred.add(number);
        }
        // System.out.println("*****" + arrayFiltred);

        sprint.setWorkedlarray(arrayFiltred);
        // List<String> arraysp = sprintRequest.getWorkedlarray();
        sprintRepository.sprintArrayOfWorkedLine(sprint.getId(), arrayFiltred);
        //log.info("Done line {} ",arrayFiltred);

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("worked sp Inserted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Number of sprint By velocity average
    @GetMapping(value = "/sprints/{pReference}/nbrSprintByVelocity")
    public ResponseEntity<List<Map.Entry<String, Float>>> getNumberOfSprintByVelocity(
            @PathVariable(value = "pReference") String pReference) {

        Project projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        List<Sprint> sprints = sprintRepository.findByProjetId(projet.getId());

        List<Integer> diffSprintTab = new ArrayList<Integer>();

        List<Integer> commitmentSprintTab = new ArrayList<Integer>();

        for (Sprint sprint : sprints) {

            int element = Math.abs(sprint.getWorkCommitment() - sprint.getWorkCompleted());
            diffSprintTab.add(element);
        }

        for (Sprint sprint : sprints) {
            commitmentSprintTab.add(sprint.getWorkCommitment());
        }

        Map<String, Float> result = this.sprintServiceImp.nbrSprintByvelocity(diffSprintTab, commitmentSprintTab, projet.getId());

        // Associate Array
        Set<Map.Entry<String, Float>> set = result.entrySet();

        List<Map.Entry<String, Float>> list = new ArrayList<>(set);

        //for (int i = 0; i < list.size(); i++) {
        // System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        //}

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
