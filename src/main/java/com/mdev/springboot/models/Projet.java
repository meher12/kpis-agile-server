package com.mdev.springboot.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "projets", uniqueConstraints = { @UniqueConstraint(columnNames = "pReference") })
/* ----- dealing with bi-directional relationships  */
//@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,   property = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Projet implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_generator")
    private Long id;

    @NotNull
    @Column(length = 15)
    private String pReference;

    @NotNull
    @Column(length = 100)
    private String titre;

    @NotNull
    @Column(length = 1200)
    private String descriptionProject;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

//    @Column(length = 10, nullable = false)
//    private int iteration_sprint;

    @OneToMany(mappedBy = "projet")
    @JsonManagedReference
    private Set<Sprint> sprints;
    
    @NotNull
    @Column(name="totalsps")
    private int totalsps;
    
   
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "users_projets", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "projets_id", referencedColumnName = "id"))
//    private Set<User> team_membres;



    public Projet() {
        super();
    }


    public Projet(@NotNull String pReference, @NotNull String titre, @NotNull String descriptionProject, Date dateDebut,
            Date dateFin, Set<Sprint> sprints, @NotNull int totalsps) {
        super();
        this.pReference = pReference;
        this.titre = titre;
        this.descriptionProject = descriptionProject;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sprints = sprints;
        this.totalsps = totalsps;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getpReference() {
        return pReference;
    }


    public void setpReference(String pReference) {
        this.pReference = pReference;
    }


    public String getTitre() {
        return titre;
    }


    public void setTitre(String titre) {
        this.titre = titre;
    }


    public String getDescriptionProject() {
        return descriptionProject;
    }


    public void setDescriptionProject(String descriptionProject) {
        this.descriptionProject = descriptionProject;
    }


    public Date getDateDebut() {
        return dateDebut;
    }


    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }


    public Date getDateFin() {
        return dateFin;
    }


    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


    public Set<Sprint> getSprints() {
        return sprints;
    }


    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }


    public int getTotalsps() {
        return totalsps;
    }


    public void setTotalsps(int totalsps) {
        this.totalsps = totalsps;
    }


    @Override
    public String toString() {
        return "Projet [id=" + id + ", pReference=" + pReference + ", titre=" + titre + ", descriptionProject="
                + descriptionProject + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", sprints=" + sprints
                + ", totalsps=" + totalsps + "]";
    }

   

}