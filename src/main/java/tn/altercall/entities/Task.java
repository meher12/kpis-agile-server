package tn.altercall.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tasks", uniqueConstraints = { @UniqueConstraint(columnNames = "tReference") })
/* ----- dealing with bi-directional relationships */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
    private Long id;

    @NotNull
    @Column(name = "title",columnDefinition="TEXT")
    private String tname;

    @NotNull
    @Column(length = 15)
    private String tReference;

    @NotNull
    @Column(name = "description", columnDefinition="TEXT")
    private String tdescription;

    @Temporal(TemporalType.DATE)
    private Date tdateDebut;

    @Temporal(TemporalType.DATE)
    private Date tdateFin;


    @Enumerated(EnumType.STRING)
    private ETask status;

    @Enumerated(EnumType.STRING)
    private ETypeTask typeTask;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date tsupdatedDate;
    
    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int estimation; 
    
    @NotNull
    @Column(columnDefinition = "integer default 0")
    private int bugs; 
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "story_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Story story;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "users_tasks", joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tasks_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name= "member_of_task", joinColumns = @JoinColumn(name = "taskAttached_id", nullable = false))
    @Column(name="mail", length = 255)
    private Set<String> emailUser = new HashSet<>();

    public Task() {
        super();
    }

    public Task(@NotNull String tname, @NotNull String tReference, @NotNull String tdescription, Date tdateDebut,
            Date tdateFin, ETask status, ETypeTask typeTask, Date tsupdatedDate, @NotNull int estimation,
            @NotNull int bugs, Story story) {
        super();
        this.tname = tname;
        this.tReference = tReference;
        this.tdescription = tdescription;
        this.tdateDebut = tdateDebut;
        this.tdateFin = tdateFin;
        this.status = status;
        this.typeTask = typeTask;
        this.tsupdatedDate = tsupdatedDate;
        this.estimation = estimation;
        this.bugs = bugs;
        this.story = story;
    }



    public Task(Long id, String tname, String tReference, String tdescription, Date tdateDebut, Date tdateFin,
                ETask status, ETypeTask typeTask, Date tsupdatedDate, int estimation, int bugs, Story story, Set<User> users, Set<String> emailUser) {
        this.id = id;
        this.tname = tname;
        this.tReference = tReference;
        this.tdescription = tdescription;
        this.tdateDebut = tdateDebut;
        this.tdateFin = tdateFin;
        this.status = status;
        this.typeTask = typeTask;
        this.tsupdatedDate = tsupdatedDate;
        this.estimation = estimation;
        this.bugs = bugs;
        this.story = story;
        this.users = users;
        this.emailUser = emailUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String gettReference() {
        return tReference;
    }

    public void settReference(String tReference) {
        this.tReference = tReference;
    }

    public String getTdescription() {
        return tdescription;
    }

    public void setTdescription(String tdescription) {
        this.tdescription = tdescription;
    }

    public Date getTdateDebut() {
        return tdateDebut;
    }

    public void setTdateDebut(Date tdateDebut) {
        this.tdateDebut = tdateDebut;
    }

    public Date getTdateFin() {
        return tdateFin;
    }

    public void setTdateFin(Date tdateFin) {
        this.tdateFin = tdateFin;
    }

    public ETask getStatus() {
        return status;
    }

    public void setStatus(ETask status) {
        this.status = status;
    }

    public ETypeTask getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(ETypeTask typeTask) {
        this.typeTask = typeTask;
    }

    public Date getTsupdatedDate() {
        return tsupdatedDate;
    }

    public void setTsupdatedDate(Date tsupdatedDate) {
        this.tsupdatedDate = tsupdatedDate;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public int getBugs() {
        return bugs;
    }

    public void setBugs(int bugs) {
        this.bugs = bugs;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<String> getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(Set<String> emailUser) {
        this.emailUser = emailUser;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", tname='" + tname + '\'' +
                ", tReference='" + tReference + '\'' +
                ", tdescription='" + tdescription + '\'' +
                ", tdateDebut=" + tdateDebut +
                ", tdateFin=" + tdateFin +
                ", status=" + status +
                ", typeTask=" + typeTask +
                ", tsupdatedDate=" + tsupdatedDate +
                ", estimation=" + estimation +
                ", bugs=" + bugs +
                ", story=" + story +
                ", users=" + users +
                ", emailUser=" + emailUser +
                '}';
    }
}
