package com.mdev.springboot.models;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sprint")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String stitre;

    @Column(length = 250, nullable = false)
    private String sdescription;

    @Temporal(TemporalType.DATE)
    private Date sdate_debut;

    @Temporal(TemporalType.DATE)
    private Date sdate_fin;

    @OneToMany(mappedBy = "story_of_sprint")
    private Set<Story> story;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "projet_id", referencedColumnName = "id")
    private Projet sprint_of_project;

    public Sprint(String stitre, String sdescription, Date sdate_debut, Date sdate_fin, Set<Story> story,
            Projet sprint_of_project) {
        super();
        this.stitre = stitre;
        this.sdescription = sdescription;
        this.sdate_debut = sdate_debut;
        this.sdate_fin = sdate_fin;
        this.story = story;
        this.sprint_of_project = sprint_of_project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStitre() {
        return stitre;
    }

    public void setStitre(String stitre) {
        this.stitre = stitre;
    }

    public String getSdescription() {
        return sdescription;
    }

    public void setSdescription(String sdescription) {
        this.sdescription = sdescription;
    }

    public Date getSdate_debut() {
        return sdate_debut;
    }

    public void setSdate_debut(Date sdate_debut) {
        this.sdate_debut = sdate_debut;
    }

    public Date getSdate_fin() {
        return sdate_fin;
    }

    public void setSdate_fin(Date sdate_fin) {
        this.sdate_fin = sdate_fin;
    }

    public Set<Story> getStory() {
        return story;
    }

    public void setStory(Set<Story> story) {
        this.story = story;
    }

    public Projet getSprint_of_project() {
        return sprint_of_project;
    }

    public void setSprint_of_project(Projet sprint_of_project) {
        this.sprint_of_project = sprint_of_project;
    }

}
