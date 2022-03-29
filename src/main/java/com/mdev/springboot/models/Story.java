package com.mdev.springboot.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Column(name = "title",columnDefinition="TEXT")
    private String stname;

    @NotNull
  //@Lob
    @Column(name = "description", columnDefinition="TEXT")
    private String stdescription;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int storyPoint;
    
    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int spCompleted;
    
    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int plusSp;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date stupdatedDate;

    //@NotNull
    @Column(length = 5)
    private Long priority;

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



    public Story(@NotNull String stReference, @NotNull String stname, @NotNull String stdescription,
            @NotNull int storyPoint, @NotNull int spCompleted, @NotNull int plusSp, Date stupdatedDate, Long priority,
            Set<Task> tasks, Sprint sprint) {
        super();
        this.stReference = stReference;
        this.stname = stname;
        this.stdescription = stdescription;
        this.storyPoint = storyPoint;
        this.spCompleted = spCompleted;
        this.plusSp = plusSp;
        this.stupdatedDate = stupdatedDate;
        this.priority = priority;
        this.tasks = tasks;
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



    public int getSpCompleted() {
        return spCompleted;
    }



    public void setSpCompleted(int spCompleted) {
        this.spCompleted = spCompleted;
    }



    public int getPlusSp() {
        return plusSp;
    }



    public void setPlusSp(int plusSp) {
        this.plusSp = plusSp;
    }



    public Date getStupdatedDate() {
        return stupdatedDate;
    }



    public void setStupdatedDate(Date stupdatedDate) {
        this.stupdatedDate = stupdatedDate;
    }



    public Long getPriority() {
        return priority;
    }



    public void setPriority(Long priority) {
        this.priority = priority;
    }



    public Set<Task> getTasks() {
        return tasks;
    }



    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
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
                + stdescription + ", storyPoint=" + storyPoint + ", spCompleted=" + spCompleted + ", plusSp=" + plusSp
                + ", stupdatedDate=" + stupdatedDate + ", priority=" + priority + ", tasks=" + tasks + ", sprint="
                + sprint + "]";
    }
    
    

}

    