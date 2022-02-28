package com.mdev.springboot.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nom;

    @Column(length = 250, nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date date_debut;

    @Temporal(TemporalType.DATE)
    private Date date_fin;

    @Column(length = 5)
    private int estimation;

    @Enumerated(EnumType.STRING)
    private ETask statut;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "story_id", referencedColumnName = "id")
    private Story story;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "developer_tasks", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<User> developer;

    public Task() {
        super();
    }

    public Task(String nom, String description, Date date_debut, Date date_fin, int estimation, ETask statut,
            Story story, Set<User> developer) {
        super();
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.estimation = estimation;
        this.statut = statut;
        this.story = story;
        this.developer = developer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public ETask getStatut() {
        return statut;
    }

    public void setStatut(ETask statut) {
        this.statut = statut;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Set<User> getDeveloper() {
        return developer;
    }

    public void setDeveloper(Set<User> developer) {
        this.developer = developer;
    }

   

}
