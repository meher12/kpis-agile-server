package com.mdev.springboot.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    public JacocoReport() {
        super();
    }

    public JacocoReport(String type, String projectname, float covered, float missed, float percentage,
            float totalpercentage, Date createdAt) {
        super();
        this.type = type;
        this.projectname = projectname;
        this.covered = covered;
        this.missed = missed;
        this.percentage = percentage;
        this.totalpercentage = totalpercentage;
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "JacocoReport [id=" + id + ", type=" + type + ", projectname=" + projectname + ", covered=" + covered
                + ", missed=" + missed + ", percentage=" + percentage + ", totalpercentage=" + totalpercentage
                + ", createdAt=" + createdAt + "]";
    }

   

}
