package com.mdev.springboot.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name = "sprints", uniqueConstraints = { @UniqueConstraint(columnNames = "sReference") })
/* ----- dealing with bi-directional relationships */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Sprint implements Serializable {

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
    @Column(name = "title",columnDefinition="TEXT")
    private String stitre;

    @NotNull
    @Column(name = "description", columnDefinition="TEXT")
    private String sdescription;

    @NotNull
    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int workCommitment;

    @NotNull
    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int workCompleted;

    @Temporal(TemporalType.DATE)
    private Date sdateDebut;

    @Temporal(TemporalType.DATE)
    private Date sdateFin;

    @Temporal(TemporalType.TIMESTAMP)
    private Date supdatedDate;

    @OneToMany(mappedBy = "sprint")
    @JsonManagedReference
    private Set<Story> stories;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projet_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Projet projet;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "days_sprints", joinColumns = @JoinColumn(name = "sprint_id"))
    @Column(name = "days_array", length = 1000)
    private List<String> daysarray;
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "ideall_sprints", joinColumns = @JoinColumn(name = "sprint_id"))
    @Column(name = "il_array", length = 1000)
    private List<String> idealLinearray;
    

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "workedl_sprints", joinColumns = @JoinColumn(name = "sprint_id"))
    @Column(name = "workedl_array", length = 1000)
    private List<String> workedlarray;

    public Sprint() {
        super();
    }

    public Sprint(@NotNull String sReference, @NotNull String stitre, @NotNull String sdescription,
            @NotNull int workCommitment, @NotNull int workCompleted, Date sdateDebut, Date sdateFin, Date supdatedDate,
            Set<Story> stories, @NotNull Projet projet, List<String> daysarray, List<String> idealLinearray,
            List<String> workedlarray) {
        super();
        this.sReference = sReference;
        this.stitre = stitre;
        this.sdescription = sdescription;
        this.workCommitment = workCommitment;
        this.workCompleted = workCompleted;
        this.sdateDebut = sdateDebut;
        this.sdateFin = sdateFin;
        this.supdatedDate = supdatedDate;
        this.stories = stories;
        this.projet = projet;
        this.daysarray = daysarray;
        this.idealLinearray = idealLinearray;
        this.workedlarray = workedlarray;
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

    public int getWorkCommitment() {
        return workCommitment;
    }

    public void setWorkCommitment(int workCommitment) {
        this.workCommitment = workCommitment;
    }

    public int getWorkCompleted() {
        return workCompleted;
    }

    public void setWorkCompleted(int workCompleted) {
        this.workCompleted = workCompleted;
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

    public Date getSupdatedDate() {
        return supdatedDate;
    }

    public void setSupdatedDate(Date supdatedDate) {
        this.supdatedDate = supdatedDate;
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

    public List<String> getDaysarray() {
        return daysarray;
    }

    public void setDaysarray(List<String> daysarray) {
        this.daysarray = daysarray;
    }

    public List<String> getIdealLinearray() {
        return idealLinearray;
    }

    public void setIdealLinearray(List<String> idealLinearray) {
        this.idealLinearray = idealLinearray;
    }

    public List<String> getWorkedlarray() {
        return workedlarray;
    }

    public void setWorkedlarray(List<String> workedlarray) {
        this.workedlarray = workedlarray;
    }

    @Override
    public String toString() {
        return "Sprint [id=" + id + ", sReference=" + sReference + ", stitre=" + stitre + ", sdescription="
                + sdescription + ", workCommitment=" + workCommitment + ", workCompleted=" + workCompleted
                + ", sdateDebut=" + sdateDebut + ", sdateFin=" + sdateFin + ", supdatedDate=" + supdatedDate
                + ", stories=" + stories + ", projet=" + projet + ", daysarray=" + daysarray + ", idealLinearray="
                + idealLinearray + ", workedlarray=" + workedlarray + "]";
    }

  

    

}
