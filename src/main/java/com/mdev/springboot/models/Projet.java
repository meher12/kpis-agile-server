package com.mdev.springboot.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "projets")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String titre;

    @Column(length = 250, nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date date_debut;

    @Temporal(TemporalType.DATE)
    private Date date_fin;

//    @Column(length = 10, nullable = false)
//    private int iteration_sprint;
    
//    @OneToMany(mappedBy = "sprint_of_project")
//    private Set<Sprint> sprints;

//    @Column(length = 20, nullable = false)
//    private String nomPO;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "users_projets", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "projets_id", referencedColumnName = "id"))
//    private Set<User> team_membres;

//    @OneToMany(mappedBy = "story_of_projet")
//    private Set<Story> stories;

   

    public Projet() {
        super();
    }

    public Projet(String titre, String description, Date date_debut, Date date_fin) {
        super();
        this.titre = titre;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

   

   

}