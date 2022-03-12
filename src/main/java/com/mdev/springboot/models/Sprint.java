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
@Table(name = "sprint", uniqueConstraints = { @UniqueConstraint(columnNames = "sReference") })
/* ----- dealing with bi-directional relationships  */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
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
    private String sReference;

    @NotNull
    @Column(length = 100)
    private String stitre;

    @NotNull
    //@Lob
    @Column(length = 500)
    private String sdescription;

    @Temporal(TemporalType.DATE)
    private Date sdateDebut;

    @Temporal(TemporalType.DATE)
    private Date sdateFin;

    @OneToMany(mappedBy = "sprint")
    @JsonManagedReference
    private Set<Story> stories;

    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projet_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Projet projet;

    public Sprint() {
        super();
    }

    public Sprint(@NotNull String sReference, @NotNull String stitre, @NotNull String sdescription, Date sdateDebut,
            Date sdateFin, Set<Story> stories, @NotNull Projet projet) {
        super();
        this.sReference = sReference;
        this.stitre = stitre;
        this.sdescription = sdescription;
        this.sdateDebut = sdateDebut;
        this.sdateFin = sdateFin;
        this.stories = stories;
        this.projet = projet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsReference() {
        return sReference;
    }

    public void setsReference(String sReference) {
        this.sReference = sReference;
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

    public Set<Story> getStories() {
        return stories;
    }

    public void setStories(Set<Story> stories) {
        this.stories = stories;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Override
    public String toString() {
        return "Sprint [id=" + id + ", sReference=" + sReference + ", stitre=" + stitre + ", sdescription="
                + sdescription + ", sdateDebut=" + sdateDebut + ", sdateFin=" + sdateFin + ", stories=" + stories
                + ", projet=" + projet + "]";
    }

  
   
    
    
}
