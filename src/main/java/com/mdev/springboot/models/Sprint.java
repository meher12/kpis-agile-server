package com.mdev.springboot.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sprint")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sprint_generator")
    private Long id;

    @NotNull
    @Column(length = 15)
    private String sUniqueID;

    @Column(length = 50, nullable = false)
    private String stitre;

    @Column(length = 250, nullable = false)
    private String sdescription;

    @Temporal(TemporalType.DATE)
    private Date sdateDebut;

    @Temporal(TemporalType.DATE)
    private Date sdateFin;

//    @OneToMany(mappedBy = "story_of_sprint")
//    private Set<Story> story;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projet_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Projet projetId;

    public Sprint() {
        super();
    }

    public Sprint(String stitre, String sdescription, Date sdateDebut, Date sdateFin, Projet projet) {
        super();
        this.stitre = stitre;
        this.sdescription = sdescription;
        this.sdateDebut = sdateDebut;
        this.sdateFin = sdateFin;
        this.projetId = projet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsUniqueID() {
        return sUniqueID;
    }

    public void setsUniqueID(String sUniqueID) {
        this.sUniqueID = sUniqueID;
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

    public Date getSdateDebut() {
        return sdateDebut;
    }

    public void setSdateDebut(Date sdateDebut) {
        this.sdateDebut = sdateDebut;
    }

    public Date getSdateFin() {
        return sdateFin;
    }

    public void setSdateFin(Date sdateFin) {
        this.sdateFin = sdateFin;
    }

    public Projet getProjetId() {
        return projetId;
    }

    public void setProjetId(Projet projetId) {
        this.projetId = projetId;
    }

    
    
}
