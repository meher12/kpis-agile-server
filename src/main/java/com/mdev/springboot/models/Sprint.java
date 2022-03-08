package com.mdev.springboot.models;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "sprint", uniqueConstraints = { @UniqueConstraint(columnNames = "sUniqueID") })
//dealing with bi-directional relationships
//@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Sprint implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sprint_generator")
    private Long id;

    @NotNull
    @Column(length = 15)
    private String sUniqueID;

    @NotNull
    @Column(length = 50)
    private String stitre;

    @NotNull
    @Column(length = 250)
    private String sdescription;

    @Temporal(TemporalType.DATE)
    private Date sdateDebut;

    @Temporal(TemporalType.DATE)
    private Date sdateFin;

//    @OneToMany(mappedBy = "story_of_sprint")
//    private Set<Story> story;

    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projet_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonBackReference
    private Projet projet;

    public Sprint() {
        super();
    }

    public Sprint(Long id, @NotNull String sUniqueID, @NotNull String stitre, @NotNull String sdescription,
            Date sdateDebut, Date sdateFin, Projet projet) {
        super();
        this.id = id;
        this.sUniqueID = sUniqueID;
        this.stitre = stitre;
        this.sdescription = sdescription;
        this.sdateDebut = sdateDebut;
        this.sdateFin = sdateFin;
        this.projet = projet;
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

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

   
    
    
}
