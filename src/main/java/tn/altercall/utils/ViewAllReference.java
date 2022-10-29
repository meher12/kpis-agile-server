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
    private Date taskStaredAt;
}
