package com.mdev.springboot.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tasks", uniqueConstraints = { @UniqueConstraint(columnNames = "tReference") })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
    private Long id;

    @NotNull
    @Column(length = 100)
    private String tname;

    @NotNull
    @Column(length = 15)
    private String tReference;

    @NotNull
    @Column(length = 1000)
    private String tdescription;

    @Temporal(TemporalType.DATE)
    private Date tdateDebut;

    @Temporal(TemporalType.DATE)
    private Date tdateFin;


    @Enumerated(EnumType.STRING)
    private ETask status;

    @Enumerated(EnumType.STRING)
    private ETypeTask typeTask;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "story_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Story story;

    public Task() {
        super();
    }

    public Task(@NotNull String tname, @NotNull String tReference, @NotNull String tdescription, Date tdateDebut,
            Date tdateFin, ETask status, ETypeTask typeTask, Story story) {
        super();
        this.tname = tname;
        this.tReference = tReference;
        this.tdescription = tdescription;
        this.tdateDebut = tdateDebut;
        this.tdateFin = tdateFin;
        this.status = status;
        this.typeTask = typeTask;
        this.story = story;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String gettReference() {
        return tReference;
    }

    public void settReference(String tReference) {
        this.tReference = tReference;
    }

    public String getTdescription() {
        return tdescription;
    }

    public void setTdescription(String tdescription) {
        this.tdescription = tdescription;
    }

    public Date getTdateDebut() {
        return tdateDebut;
    }

    public void setTdateDebut(Date tdateDebut) {
        this.tdateDebut = tdateDebut;
    }

    public Date getTdateFin() {
        return tdateFin;
    }

    public void setTdateFin(Date tdateFin) {
        this.tdateFin = tdateFin;
    }

    public ETask getStatus() {
        return status;
    }

    public void setStatus(ETask status) {
        this.status = status;
    }

    public ETypeTask getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(ETypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", tname=" + tname + ", tReference=" + tReference + ", tdescription=" + tdescription
                + ", tdateDebut=" + tdateDebut + ", tdateFin=" + tdateFin + ", status=" + status + ", typeTask="
                + typeTask + ", story=" + story + "]";
    }

    

}
