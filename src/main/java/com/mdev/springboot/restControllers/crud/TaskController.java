package com.mdev.springboot.restControllers.crud;

import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mdev.springboot.exception.ApiResourceNotFoundException;
import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.Story;
import com.mdev.springboot.models.Task;
import com.mdev.springboot.repository.StoryRepository;
import com.mdev.springboot.repository.TaskRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    StoryRepository storyRepository;

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
    public ResponseEntity<Task> createTask(@PathVariable(value = "story_id") Long story_id,
            @RequestBody Task taskRequest) {
        Task task = storyRepository.findById(story_id).map(story -> {
            taskRequest.setStory(story);

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
        task.setStatus(taskRequest.getStatus());
        task.setTypeTask(taskRequest.getTypeTask());

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

}
