package tn.altercall.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "jcoverage"
/*
, uniqueConstraints = { @UniqueConstraint(columnNames = "projectname") }
 */
)
public class JacocoReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jcoverage_generator")
    private Long id;

    private String type;
    private String projectname;
    private float covered;
    private float missed;
    private float percentage;
    private float totalpercentage;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

   private String projectRef;
/*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Project project;*/

    public JacocoReport() {
        super();
    }

    public JacocoReport(String type, String projectname, float covered, float missed, float percentage,
            float totalpercentage, Date createdAt, String projectRef) {
        super();
        this.type = type;
        this.projectname = projectname;
        this.covered = covered;
        this.missed = missed;
        this.percentage = percentage;
        this.totalpercentage = totalpercentage;
        this.createdAt = createdAt;
        this.projectRef = projectRef;
    }

    public JacocoReport(Long id, String type, String projectname, float covered, float missed, float percentage, float totalpercentage, Date createdAt, String projectRef) {
        this.id = id;
        this.type = type;
        this.projectname = projectname;
        this.covered = covered;
        this.missed = missed;
        this.percentage = percentage;
        this.totalpercentage = totalpercentage;
        this.createdAt = createdAt;
        this.projectRef = projectRef;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public float getCovered() {
        return covered;
    }

    public void setCovered(float covered) {
        this.covered = covered;
    }

    public float getMissed() {
        return missed;
    }

    public void setMissed(float missed) {
        this.missed = missed;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getTotalpercentage() {
        return totalpercentage;
    }

    public void setTotalpercentage(float totalpercentage) {
        this.totalpercentage = totalpercentage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getProjectRef() {
        return projectRef;
    }

    public void setProjectRef(String projectRef) {
        this.projectRef = projectRef;
    }


}
