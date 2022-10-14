package tn.altercall.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.altercall.entities.Project;
import tn.altercall.entities.User;
import tn.altercall.exception.ApiResourceNotFoundException;
import tn.altercall.exception.ResourceNotFoundException;
import tn.altercall.repository.*;
import tn.altercall.services.ProjectServiceImp;
import tn.altercall.utils.*;

import java.text.ParseException;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class ProjetController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProjetController.class);
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
    @Autowired
    UserRepository userRepository;

    public static String[] toArray(String emails) {
        if (emails == null)
            return new String[0];

        String[] tmp = emails.split(",");
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = tmp[i].trim();
        }
        return tmp;
    }

    // get all project by Title or All
    // @PreAuthorize("hasRole('PRODUCTOWNER')")
    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects(@RequestParam(required = false) String titre) {
        List<Project> projets = new ArrayList<Project>();

        if (titre == null)
            projetRepository.findAll().forEach(projets::add);
        else
            projetRepository.findByTitre(titre).forEach(projets::add);

        if (projets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Comparator<Project> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getDateDebut().getTime()).compareTo(c2.getDateDebut().getTime());
        };

        Collections.sort(projets, comparator);
        return new ResponseEntity<>(projets, HttpStatus.OK);
    }

    // get project by Id
    // @PreAuthorize("hasRole('PRODUCTOWNER')")
    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) {
        Project projet = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with id = " + id));
        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    //// get project by reference
    @RequestMapping(value = "/projects/{pReference}/", method = RequestMethod.GET)
    public ResponseEntity<Project> getProjectBypReference(@PathVariable("pReference") String pReference) {

        Project projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        return new ResponseEntity<>(projet, HttpStatus.OK);
    }

    // create project
    //@PreAuthorize("hasRole('PRODUCTOWNER')")
    @PostMapping("/projects/")
    public ResponseEntity<Project> createProjet(@RequestBody Project projet) {
        /*Set<User> users = new HashSet<>();
        var userFound = new User();

        for (String email : projet.getEmailMember()) {
            userFound = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found member with email:" + email));
            userFound.setEmail(userFound.getEmail());
            userFound.setUsername(userFound.getUsername());
            userFound.setRoles(userFound.getRoles());
            // userFound.setPassword(userFound.getPassword());
            users.add(userFound);
        }

        projet.setUsers(users);*/
        Project _projet = projetRepository.save(projet);
        return new ResponseEntity<>(_projet, HttpStatus.CREATED);
    }

    // update project by id
    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProjet(@PathVariable("id") Long id, @RequestBody Project projetDetails) {

        Project _projet = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id = " + id));

        _projet.setTitre(projetDetails.getTitre());
        _projet.setDescriptionProject(projetDetails.getDescriptionProject());
        _projet.setDateDebut(projetDetails.getDateDebut());
        _projet.setTotalstorypointsinitiallycounts(projetDetails.getTotalstorypointsinitiallycounts());
        _projet.setDateFin(projetDetails.getDateFin());

        //  update member team
        //LOGGER.info("user is {}", projetDetails.getEmailMember());
     /*   Set<User> users = new HashSet<>();
        Set<Role> rl = new HashSet<>();


        //  var userFound = new User();
        for (String email : projetDetails.getEmailMember()) {
            var userFound = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found member with email:" + email));
            userFound.setEmail(userFound.getEmail());
            userFound.setUsername(userFound.getUsername());
            userFound.setRoles(userFound.getRoles());
            //  userFound.setPassword(userFound.getPassword());

            users.add(userFound);
        }
        _projet.setUsers(users);
        _projet.setEmailMember(projetDetails.getEmailMember());*/


        return new ResponseEntity<>(projetRepository.saveAndFlush(_projet), HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProjectById(@PathVariable Long id) {

        Project projet = this.projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found project with id: " + id));
        this.projetRepository.delete(projet);

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

    //Delete all project
    @DeleteMapping("/projects/")
    public ResponseEntity<Map<String, Boolean>> deleteAllProjects() throws ApiResourceNotFoundException {

        List<Project> deletedProject = projetRepository.findAll();
        Map<String, Boolean> response = new HashMap<String, Boolean>();

        response.put("Not found Projects to Delete it!", Boolean.FALSE);
        if (deletedProject.isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        } else {
            projetRepository.deleteAll();
            response.put("Projects Deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }

    }

    // update all story points
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


    //  product burndown chart by Project reference in params
    @GetMapping("/projects/productbdchart")
    public ResponseEntity<ProductDate> productBurndownChart(@RequestParam("pReference") String pReference) {

        storyRepository.StoryPointUpdate();
        sprintRepository.sprintStoryPointUpdate();

        storyRepository.updatePlusSp();
        sprintRepository.updateMoreSp();

        taskRepository.tasktimeUpdate();
        projetRepository.totalSpInProject();

        Project projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        // Completed SP table in project for product brundown chart
        ArrayList<String> spDoneFromSprint = sprintRepository.getListSpCompleted(pReference);

        log.info("********  spDoneFromSprint:  {}", spDoneFromSprint);

        // new SP table in project for product brundown chart
        ArrayList<String> newSpFromSprint = sprintRepository.getListMoreSp(pReference);

        log.info("******* NewSpFromSprint: {}", newSpFromSprint);


        int sumSp = projet.getTotalstorypointsinitiallycounts();

        projet.setpSpwrked(spDoneFromSprint);
        ArrayList<String> arraySpWorked = (ArrayList<String>) projet.getpSpwrked();
        projetRepository.projectSpCompletedArray(projet.getId(), arraySpWorked);

        projet.setpMoresp(newSpFromSprint);
        ArrayList<String> arraySpmore = (ArrayList<String>) projet.getpMoresp();
        projetRepository.projectMoreSpArray(projet.getId(), arraySpmore);

        projet.setpSpCommitment(this.projectServiceImp.productBurndownChart(sumSp, spDoneFromSprint, newSpFromSprint, pReference));
        projetRepository.spCommitmentArray(projet.getId(), projet.getpSpCommitment());

        ArrayList<String> remainingSp = new ArrayList<>(projet.getpSpCommitment());
        log.info("******* remainingSp: {}", remainingSp);

        spDoneFromSprint.set(0, "0");
        newSpFromSprint.set(0, "0");
        /*  remainingSp.add(0, String.valueOf(sumSp));*/

        ProductDate productData = new ProductDate(spDoneFromSprint, newSpFromSprint, remainingSp);
        return new ResponseEntity<>(productData, HttpStatus.OK);

    }

    // Projects by release burndown chart
    @GetMapping("/projects/releasebdchart/{pReference}")
    public ResponseEntity<Map<String, Boolean>> pReleaseBurndownChart(@PathVariable("pReference") String pReference) {

        storyRepository.StoryPointUpdate();
        sprintRepository.sprintStoryPointUpdate();

        storyRepository.updatePlusSp();
        sprintRepository.updateMoreSp();

        taskRepository.tasktimeUpdate();
        projetRepository.totalSpInProject();

        Project projet = projetRepository.findBypReference(pReference)
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

        Project projet = projetRepository.findBypReference(pReference)
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

        Project projet = projetRepository.findBypReference(pReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Project with Reference : " + pReference));

        PairArrays pair = projectServiceImp.listTaskByStatus(projet.getpReference());
        return new ResponseEntity<>(pair, HttpStatus.OK);

    }

    // select date_debut in task by project reference
    @GetMapping("/projects/listStartDateTask/{pReference}")
    public ResponseEntity<ArrayList<String>> getListTaskStartDateBypRef(@PathVariable("pReference") String pReference) {

        Project projet = projetRepository.findBypReference(pReference)
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
    public ResponseEntity<Map<String, String>> getpercentageStoryPointsInProject(@PathVariable("pReference") String pReference) {

        Project projet = projetRepository.findBypReference(pReference)
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
        String totalspcompleted = String.valueOf(projet.getTotalspCompleted());

        // total sp initialized
        String totalstorypointsinitiallycounts = String.valueOf(projet.getTotalstorypointsinitiallycounts());

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
                                                                      @RequestBody ArrayList<TasksBugs> dataRequest) {

        Map<Date, Date> mapDate = new HashMap<Date, Date>();

        for (TasksBugs tasksBugs : dataRequest) {
            mapDate.put(tasksBugs.getStartDate(), tasksBugs.getEndDate());
        }
        // System.out.println(mapDate);
        DataTaskBugChart dataChart = this.projectServiceImp.getTasksBugs(pReference, mapDate);
        return new ResponseEntity<>(dataChart, HttpStatus.OK);
    }


    // Add, update member
    @PutMapping(value = "/project/addmember/{id}")
    public ResponseEntity<Map<String, Boolean>> createTeam(@PathVariable("id") Long id,
                                                           @RequestBody Set<Object> teamRequest) {

        Project project = projetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectId " + id + "not found"));

        Set<String> arrayFiltred = new HashSet<>();
        Set<String> arrayJson = new HashSet<>();
        Set<String> arrayMail = new HashSet<>();

        //log.info("Request data from client {}",teamRequest);

        teamRequest.forEach(name -> {
            arrayJson.add(name.toString());
        });

        for (String item : arrayJson) {
            String mail = item.replaceAll("emailMember=", "");
            arrayFiltred.add(mail);
        }
        // log.info("*****{}", arrayFiltred );

        for (String filterdMail : arrayFiltred) {
            filterdMail = filterdMail.replaceAll("[\\{|\\}]", "");
            arrayMail.add(filterdMail);
        }
        log.info("arrayMail {}", arrayMail);

        project.setEmailMember(arrayMail);
        Set<User> users = new HashSet<>();

        for (String email : arrayMail) {
            var userFound = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found member with email:" + email));
            userFound.setEmail(userFound.getEmail());
            userFound.setUsername(userFound.getUsername());
            userFound.setRoles(userFound.getRoles());

            users.add(userFound);
        }
        project.setUsers(users);
        projetRepository.saveAndFlush(project);

        Map<String, Boolean> response = new HashMap<>();
        response.put("team Inserted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
