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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "projets", uniqueConstraints = { @UniqueConstraint(columnNames = "pReference") })
/* ----- dealing with bi-directional relationships */
//@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,   property = "id")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Projet implements Serializable {

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
    @Column(name = "title",columnDefinition="TEXT")
    private String titre;

    @NotNull
    //@Lob
    @Column(name = "description", columnDefinition="TEXT")
    private String descriptionProject;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @OneToMany(mappedBy = "projet")
    @JsonManagedReference
    private Set<Sprint> sprints;

    @NotNull
    @Column(name = "totalspCommitment")
    private int totalspCommitment;

    @NotNull
    @Column(length = 5)
    private int totalspCompleted;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pupdatedDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "projet_sp_commitment", joinColumns = @JoinColumn(name = "projet_id"))
    @Column(name = "project_sp_commitment", length = 1000)
    private List<String> pSpCommitment;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "projet_sp_completed", joinColumns = @JoinColumn(name = "projet_id"))
    @Column(name = "project_sp_completed", length = 1000)
    private List<String> pSpwrked;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "projet_more_sp", joinColumns = @JoinColumn(name = "projet_id"))
    @Column(name = "project_more_sp", length = 1000)
    private List<String> pMoresp;

//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "users_projets", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "projets_id", referencedColumnName = "id"))
//    private Set<User> team_membres;

    public Projet() {
        super();
    }

    public Projet(@NotNull String pReference, @NotNull String titre, @NotNull String descriptionProject, Date dateDebut,
            Date dateFin, Set<Sprint> sprints, @NotNull int totalspCommitment, @NotNull int totalspCompleted,
            Date pupdatedDate, List<String> pSpCommitment, List<String> pSpwrked, List<String> pMoresp) {
        super();
        this.pReference = pReference;
        this.titre = titre;
        this.descriptionProject = descriptionProject;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sprints = sprints;
        this.totalspCommitment = totalspCommitment;
        this.totalspCompleted = totalspCompleted;
        this.pupdatedDate = pupdatedDate;
        this.pSpCommitment = pSpCommitment;
        this.pSpwrked = pSpwrked;
        this.pMoresp = pMoresp;
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

    public int getTotalspCommitment() {
        return totalspCommitment;
    }

    public void setTotalspCommitment(int totalspCommitment) {
        this.totalspCommitment = totalspCommitment;
    }

    public int getTotalspCompleted() {
        return totalspCompleted;
    }

    public void setTotalspCompleted(int totalspCompleted) {
        this.totalspCompleted = totalspCompleted;
    }

    public Date getPupdatedDate() {
        return pupdatedDate;
    }

    public void setPupdatedDate(Date pupdatedDate) {
        this.pupdatedDate = pupdatedDate;
    }

    public List<String> getpSpCommitment() {
        return pSpCommitment;
    }

    public void setpSpCommitment(List<String> pSpCommitment) {
        this.pSpCommitment = pSpCommitment;
    }

    public List<String> getpSpwrked() {
        return pSpwrked;
    }

    public void setpSpwrked(List<String> pSpwrked) {
        this.pSpwrked = pSpwrked;
    }

    public List<String> getpMoresp() {
        return pMoresp;
    }

    public void setpMoresp(List<String> pMoresp) {
        this.pMoresp = pMoresp;
    }

    @Override
    public String toString() {
        return "Projet [id=" + id + ", pReference=" + pReference + ", titre=" + titre + ", descriptionProject="
                + descriptionProject + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", sprints=" + sprints
                + ", totalspCommitment=" + totalspCommitment + ", totalspCompleted=" + totalspCompleted
                + ", pupdatedDate=" + pupdatedDate + ", pSpCommitment=" + pSpCommitment + ", pSpwrked=" + pSpwrked
                + ", pMoresp=" + pMoresp + "]";
    }

}