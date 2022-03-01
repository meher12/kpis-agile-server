package com.mdev.springboot.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "story")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 250, nullable = false)
    private String description;

    @Column(length = 5)
    private int story_point;

    @Column(length = 5)
    private int priorite;

    @OneToMany(mappedBy = "story")
    private Set<Task> task;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "sprint_id", referencedColumnName = "id")
    private Sprint story_of_sprint;

//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "projet_id", referencedColumnName = "id")
//    private Projet story_of_projet;

    public Story(String name, String description, int story_point, int priorite, Set<Task> task, Sprint story_of_sprint) {
        super();
        this.name = name;
        this.description = description;
        this.story_point = story_point;
        this.priorite = priorite;
        this.task = task;
        this.story_of_sprint = story_of_sprint;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStory_point() {
        return story_point;
    }

    public void setStory_point(int story_point) {
        this.story_point = story_point;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }

    public Sprint getStory_of_sprint() {
        return story_of_sprint;
    }

    public void setStory_of_sprint(Sprint story_of_sprint) {
        this.story_of_sprint = story_of_sprint;
    }

   

}