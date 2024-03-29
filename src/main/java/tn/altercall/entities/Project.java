package tn.altercall.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "projects", uniqueConstraints = {@UniqueConstraint(columnNames = "pReference")})
/* ----- dealing with bi-directional relationships */
//@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class,   property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project implements Serializable {

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
    @Column(name = "title", columnDefinition = "TEXT")
    private String titre;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int totalstorypointsinitiallycounts;

    @NotNull
    @Column(name = "description", columnDefinition = "TEXT")
    private String descriptionProject;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @OneToMany(mappedBy = "projet")
    @JsonManagedReference
    private Set<Sprint> sprints;

   /* @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private Set<JacocoReport> reports;*/


    @NotNull
    @Column(name = "totalspCommitment", columnDefinition = "integer default 0")
    private int totalspCommitment;

    @NotNull
    @NotNull
    @Column(columnDefinition = "integer default 0")
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


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "projet_percentage_spc", joinColumns = @JoinColumn(name = "projet_id"))
    @Column(name = "percentage_spc", length = 1000)
    private List<String> percentage_spc;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "users_projects",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name= "member_of_project", joinColumns = @JoinColumn(name = "project_id", nullable = false)/*,
            uniqueConstraints = @UniqueConstraint(columnNames = {"project_id"})*/)
    @Column(name="email", length = 255)
    private Set<String> emailMember = new HashSet<>();



    public Project() {
    }

    //for unit test
    public Project(Long id, String titre, int totalstorypointsinitiallycounts, String descriptionProject) {
        this.id = id;
        this.titre = titre;
        this.totalstorypointsinitiallycounts = totalstorypointsinitiallycounts;
        this.descriptionProject = descriptionProject;
    }


    public Project(Long id, String pReference, String titre, int totalstorypointsinitiallycounts, String descriptionProject, Date dateDebut, Date dateFin, Set<Sprint> sprints, int totalspCommitment, int totalspCompleted, Date pupdatedDate, List<String> pSpCommitment, List<String> pSpwrked, List<String> pMoresp,
                   List<String> percentage_spc, Set<User> users, Set<String> emailMember) {
        this.id = id;
        this.pReference = pReference;
        this.titre = titre;
        this.totalstorypointsinitiallycounts = totalstorypointsinitiallycounts;
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
        this.percentage_spc = percentage_spc;
        this.users = users;
        this.emailMember = emailMember;
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


    public int getTotalstorypointsinitiallycounts() {
        return totalstorypointsinitiallycounts;
    }

    public void setTotalstorypointsinitiallycounts(int totalstorypointsinitiallycounts) {
        this.totalstorypointsinitiallycounts = totalstorypointsinitiallycounts;
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

    public List<String> getPercentage_spc() {
        return percentage_spc;
    }

    public void setPercentage_spc(List<String> percentage_spc) {
        this.percentage_spc = percentage_spc;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<String> getEmailMember() {
        return emailMember;
    }

    public void setEmailMember(Set<String> emailMember) {
        this.emailMember = emailMember;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", pReference='" + pReference + '\'' +
                ", titre='" + titre + '\'' +
                ", totalstorypointsinitiallycounts=" + totalstorypointsinitiallycounts +
                ", descriptionProject='" + descriptionProject + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", sprints=" + sprints +
                ", totalspCommitment=" + totalspCommitment +
                ", totalspCompleted=" + totalspCompleted +
                ", pupdatedDate=" + pupdatedDate +
                ", pSpCommitment=" + pSpCommitment +
                ", pSpwrked=" + pSpwrked +
                ", pMoresp=" + pMoresp +
                ", percentage_spc=" + percentage_spc +
                ", users=" + users +
                ", emailMember=" + emailMember +
                '}';
    }
}