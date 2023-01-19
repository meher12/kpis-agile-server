package tn.altercall.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ByRefView", uniqueConstraints = {@UniqueConstraint(columnNames = "refTask")})
public class ViewAllReference {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "byview_generator")
    private Long id;
    private String refProject;
    private String titleProject;
    private String refSprint;
    private String titleSprint;
    private String refStory;
    private String titleStory;
    private String refTask;
    private String titleTask;

    private String storyTitle;

    private Date taskStaredAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefProject() {
        return refProject;
    }

    public void setRefProject(String refProject) {
        this.refProject = refProject;
    }

    public String getTitleProject() {
        return titleProject;
    }

    public void setTitleProject(String titleProject) {
        this.titleProject = titleProject;
    }

    public String getRefSprint() {
        return refSprint;
    }

    public void setRefSprint(String refSprint) {
        this.refSprint = refSprint;
    }

    public String getTitleSprint() {
        return titleSprint;
    }

    public void setTitleSprint(String titleSprint) {
        this.titleSprint = titleSprint;
    }

    public String getRefStory() {
        return refStory;
    }

    public void setRefStory(String refStory) {
        this.refStory = refStory;
    }

    public String getTitleStory() {
        return titleStory;
    }

    public void setTitleStory(String titleStory) {
        this.titleStory = titleStory;
    }

    public String getRefTask() {
        return refTask;
    }

    public void setRefTask(String refTask) {
        this.refTask = refTask;
    }

    public String getTitleTask() {
        return titleTask;
    }

    public void setTitleTask(String titleTask) {
        this.titleTask = titleTask;
    }

    public Date getTaskStaredAt() {
        return taskStaredAt;
    }

    public void setTaskStaredAt(Date taskStaredAt) {
        this.taskStaredAt = taskStaredAt;
    }



    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }
}


