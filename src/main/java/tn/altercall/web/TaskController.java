package tn.altercall.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.altercall.entities.*;
import tn.altercall.exception.ApiResourceNotFoundException;
import tn.altercall.exception.ResourceNotFoundException;
import tn.altercall.repository.StoryRepository;
import tn.altercall.repository.TaskRepository;
import tn.altercall.repository.UserRepository;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
@Slf4j
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    UserRepository userRepository;


    // search by story reference
    @GetMapping("/searchBySTReference")
    public ResponseEntity<List<Task>> getAllTaskBySTReference(@RequestParam(required = false) String storyReference) {
        // try {
        var story = new Story();
        List<Task> tasks = new ArrayList<>();

        if (storyReference == null) {
            taskRepository.findAll().forEach(tasks::add);
        }
        else {
            story = storyRepository.findBystReference(storyReference)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Story with reference: " + storyReference));

            taskRepository.findByStoryId(story.getId()).forEach(tasks::add);
        }

        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Comparator<Task> comparator = (c1, c2) -> Long.valueOf(c1.getTdateDebut().getTime()).compareTo(c2.getTdateDebut().getTime());

        Collections.sort(tasks, comparator);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
       /* } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
    }

    // get All Task By StoryId
    @RequestMapping(value = "/stories/{story_id}/tasks")
    ResponseEntity<List<Task>> getAllTaskByStoryId(@PathVariable("story_id") Long story_id) {
        if (!storyRepository.existsById(story_id)) {
            throw new ResourceNotFoundException("Not found Story with id = " + story_id);
        }

        List<Task> tasks = taskRepository.findByStoryId(story_id);

        Comparator<Task> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getTdateDebut().getTime()).compareTo(c2.getTdateDebut().getTime());
        };

        Collections.sort(tasks, comparator);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // get all tasks
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTask() {

        List<Task> tasks = taskRepository.findAll();

        Comparator<Task> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getTdateDebut().getTime()).compareTo(c2.getTdateDebut().getTime());
        };

        Collections.sort(tasks, comparator);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // get All Tasks By Story Reference
    @RequestMapping(value = "/{stReference}/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTaskByStoryReference(
            @PathVariable(value = "stReference") String stReference) {
        Story story = storyRepository.findBystReference(stReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Story with Reference : " + stReference));
        List<Task> tasks = taskRepository.findByStoryId(story.getId());

        Comparator<Task> comparator = (c1, c2) -> {
            return Long.valueOf(c1.getTdateDebut().getTime()).compareTo(c2.getTdateDebut().getTime());
        };

        Collections.sort(tasks, comparator);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // get Task by id
    @GetMapping("/tasks/task/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ApiResourceNotFoundException("Not found Task with id = " + id));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    //// get task by reference
    @RequestMapping(value = "/task/{tReference}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTaskBysReference(@PathVariable("tReference") String tReference) {

        Task task = taskRepository.findBytReference(tReference)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Story with Reference : " + tReference));

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    // create Task
    @PostMapping("/story/{story_id}/task")
    public ResponseEntity<Task> createTask(@PathVariable(value = "story_id") Long story_id, @RequestBody Task taskRequest) {

       // Set<User> users = new HashSet<>();

        Task task = storyRepository.findById(story_id).map(story -> {
            taskRequest.setStory(story);

           /* for (String email : taskRequest.getEmailUser()) {
                var userFound = userRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found user with email:" + email));
                //log.info("************ {}", userFound);
                userFound.setEmail(userFound.getEmail());
                userFound.setUsername(userFound.getUsername());
                userFound.setRoles(userFound.getRoles());
                // userFound.setPassword(userFound.getPassword());
                users.add(userFound);
            }
            taskRequest.setUsers(users);*/

            return taskRepository.save(taskRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Story with id = " + story_id));

        return new ResponseEntity<>(task, HttpStatus.CREATED);


    }

    // update Task
    @RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task taskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task Id " + id + "not found"));

        task.setTname(taskRequest.getTname());
        task.setTdescription(taskRequest.getTdescription());
        task.setTdateDebut(taskRequest.getTdateDebut());
        task.setTdateFin(taskRequest.getTdateFin());
        task.setEstimation(taskRequest.getEstimation());
        task.setBugs(taskRequest.getBugs());
        task.setStatus(taskRequest.getStatus());
        task.setTypeTask(taskRequest.getTypeTask());

        // update member of task
        /*Set<User> users = new HashSet<>();

        for (String email : taskRequest.getEmailUser()) {
            var userFound = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found user with email:" + email));
            log.info("************ {}", userFound);
            userFound.setEmail(userFound.getEmail());
            userFound.setUsername(userFound.getUsername());
            userFound.setRoles(userFound.getRoles());
            // userFound.setPassword(userFound.getPassword());
            users.add(userFound);
        }

        task.setUsers(users);
        task.setEmailUser(taskRequest.getEmailUser());*/

        return new ResponseEntity<>(taskRepository.save(task), HttpStatus.OK);
    }

    // delete Task By id
    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteTaskById(@PathVariable Long id) {

        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found task with id: " + id));
        this.taskRepository.delete(task);

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // delete All Task Of Story
    @DeleteMapping("/story/{story_id}/tasks")
    public ResponseEntity<List<Task>> deleteAllTaskOfStory(@PathVariable(value = "story_id") Long story_id) {
        if (!storyRepository.existsById(story_id)) {
            throw new ResourceNotFoundException("Not found Story with id = " + story_id);
        }

        taskRepository.deleteAllByStoryId(story_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // update tasks table
    @GetMapping("/task/tasktimeUpdate")
    public ResponseEntity<Map<String, Boolean>> updateTasktable() {
        this.taskRepository.tasktimeUpdate();
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("Updated table task", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    // Add, update member
    @PutMapping(value = "/task/addmember/{id}")
    public ResponseEntity<Task> createTeamForTask(@PathVariable("id") Long id,
                                                           @RequestBody Set<Object> teamRequest) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TaskId " + id + "not found"));

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
        //log.info("arrayMail {}", arrayMail);

        task.setEmailUser(arrayMail);
        Set<User> users = new HashSet<>();

        for (String email : arrayMail) {
            var userFound = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found user with email:" + email));
            userFound.setEmail(userFound.getEmail());
            userFound.setUsername(userFound.getUsername());
            userFound.setRoles(userFound.getRoles());

            users.add(userFound);
        }
        task.setUsers(users);
        taskRepository.saveAndFlush(task);

      /*  Map<String, Boolean> response = new HashMap<>();
        response.put("team Inserted", Boolean.TRUE);
        return ResponseEntity.ok(response);
       */
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

}
