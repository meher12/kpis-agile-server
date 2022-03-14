package com.mdev.springboot.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "story", uniqueConstraints = {@UniqueConstraint(columnNames = "stReference") })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Story implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "story_generator")
    private Long id;
    
    @NotNull
    @Column(length = 15)
    private String stReference;

    @NotNull
    @Column(length = 100)
    private String stname;

    @NotNull
    @Column(length = 1000)
    private String stdescription;

    //@NotNull
    @Column(length = 5)
    private int storyPoint;

    //@NotNull
    @Column(length = 5)
    private int priority;

    @OneToMany(mappedBy = "story")
    @JsonManagedReference
    private Set<Task> tasks;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "sprint_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Sprint sprint;
    
    

    public Story() {
        super();
    }


    public Story(@NotNull String stReference, @NotNull String stname, String stdescription, int storyPoint,
            int priorite, Set<Task> task, Sprint sprint) {
        super();
        this.stReference = stReference;
        this.stname = stname;
        this.stdescription = stdescription;
        this.storyPoint = storyPoint;
        this.priority = priorite;
        this.tasks = task;
        this.sprint = sprint;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getStReference() {
        return stReference;
    }


    public void setStReference(String stReference) {
        this.stReference = stReference;
    }


    public String getStname() {
        return stname;
    }


    public void setStname(String stname) {
        this.stname = stname;
    }


    public String getStdescription() {
        return stdescription;
    }


    public void setStdescription(String stdescription) {
        this.stdescription = stdescription;
    }


    public int getStoryPoint() {
        return storyPoint;
    }


    public void setStoryPoint(int storyPoint) {
        this.storyPoint = storyPoint;
    }


   

    public int getPriority() {
        return priority;
    }


    public void setPriority(int priority) {
        this.priority = priority;
    }


    public Set<Task> getTask() {
        return tasks;
    }


    public void setTask(Set<Task> task) {
        this.tasks = task;
    }


    public Sprint getSprint() {
        return sprint;
    }


    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }


    @Override
    public String toString() {
        return "Story [id=" + id + ", stReference=" + stReference + ", stname=" + stname + ", stdescription="
                + stdescription + ", storyPoint=" + storyPoint + ", priority=" + priority + ", task=" + tasks
                + ", sprint=" + sprint + "]";
    }
    
    


   

}