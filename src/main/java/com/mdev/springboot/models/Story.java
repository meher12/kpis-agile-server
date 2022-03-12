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

    @Column(length = 1000)
    private String stdescription;

    @Column(length = 5)
    private int storyPoint;

    @Column(length = 5)
    private int priorite;

    @OneToMany(mappedBy = "story")
    @JsonManagedReference
    private Set<Task> task;

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
        this.priorite = priorite;
        this.task = task;
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


    public Sprint getSprint() {
        return sprint;
    }


    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }


    @Override
    public String toString() {
        return "Story [id=" + id + ", stReference=" + stReference + ", stname=" + stname + ", stdescription="
                + stdescription + ", storyPoint=" + storyPoint + ", priorite=" + priorite + ", task=" + task
                + ", sprint=" + sprint + "]";
    }
    
    


   

}