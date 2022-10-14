package tn.altercall.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.altercall.entities.Project;

@Repository
public interface ProjetRepository extends JpaRepository<Project, Long> {

    List<Project> findByTitre(String titre);

    Optional<Project> findBypReference(String pReference);

    @Transactional
    @Modifying
    @Query(value = "UPDATE projects SET pupdated_date = (SELECT now()), totalsp_commitment = (SELECT COALESCE(SUM(sp.work_commitment),0) FROM sprints sp WHERE projects.id = sp.projet_id),"
            + "totalsp_completed = (SELECT COALESCE(SUM(sp.work_completed),0) FROM sprints sp WHERE projects.id = sp.projet_id)", nativeQuery = true)
    void totalSpInProject();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO projet_sp_commitment (projet_id, project_sp_commitment) VALUES (:projet_id, ARRAY[:project_sp_commitment])", nativeQuery = true)
    void spCommitmentArray(Long projet_id, List<String> project_sp_commitment);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO projet_sp_completed (projet_id, project_sp_completed) VALUES (:projet_id, ARRAY[:project_sp_completed])", nativeQuery = true)
    void projectSpCompletedArray(Long projet_id, ArrayList<String> project_sp_completed);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO projet_more_sp (projet_id, project_more_sp) VALUES (:projet_id, ARRAY[:project_more_sp])", nativeQuery = true)
    void projectMoreSpArray(Long projet_id, ArrayList<String> project_more_sp);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO projet_percentage_spc (projet_id, percentage_spc) VALUES (:projet_id, ARRAY[:percentage_spc])", nativeQuery = true)
    void percentageSpcArray(Long projet_id, List<String> percentage_spc);

    // select List status tasks in project
    @Query(value = "select tasks.status as status, count(tasks.status) as count from tasks"
            + " join story  on story.id = tasks.story_id" + " join sprints on sprints.id = story.sprint_id"
            + " join projects on projects.id = sprints.projet_id"
            + " AND p_reference=?1 group by tasks.status ORDER BY tasks.status ASC", nativeQuery = true)
    List<String[]> getListStatusTasks(String p_reference);

    // select count status Scheduled in tasks by project ref
    @Query(value = "select count(status) from tasks" + " join story on story.id = tasks.story_id"
            + " join sprints on sprints.id = story.sprint_id"
            + " join projects on projects.id = sprints.projet_id AND p_reference =:p_reference"
            + " WHERE status='Scheduled' and tdate_debut BETWEEN :start AND :end ;", nativeQuery = true)
    float getCountStatusScheduled(@Param("p_reference") String p_reference, @Param("start") Date start,
            @Param("end") Date end);

    // select count status In_progress in tasks by project ref
    @Query(value = "select count(status) from tasks" + " join story on story.id = tasks.story_id"
            + " join sprints on sprints.id = story.sprint_id"
            + " join projects on projects.id = sprints.projet_id AND p_reference =:p_reference"
            + " WHERE status='In_progress' and tdate_debut BETWEEN :start AND :end ;", nativeQuery = true)
    float getCountStatusInprogress(@Param("p_reference") String p_reference, @Param("start") Date start,
            @Param("end") Date end);

    // select tdate_debut in task by project ref
    @Query(value = "select tdate_debut from tasks" + " join story on story.id = tasks.story_id"
            + " join sprints on sprints.id = story.sprint_id"
            + " join projects on projects.id = sprints.projet_id AND p_reference=:preference ORDER BY tdate_debut ASC", nativeQuery = true)
    ArrayList<String> getListTaskStartDate(String preference);

    // sum tasks has bugs
    @Query(value = "select COALESCE(SUM(bugs),0) from tasks join story on story.id = tasks.story_id join sprints on sprints.id = story.sprint_id"
            + " join projects on projects.id = sprints.projet_id AND p_reference =:p_reference"
            + " where bugs!=0 AND (tdate_debut BETWEEN :start AND :end ) ", nativeQuery = true)
    int getSumBugsTask(@Param("p_reference") String p_reference, @Param("start") Date start, @Param("end") Date end);

    // sum tasks not has bugs
    @Query(value = "select count(bugs) from tasks join story on story.id = tasks.story_id join sprints on sprints.id = story.sprint_id"
            + " join projects on projects.id = sprints.projet_id AND p_reference =:p_reference"
            + " where bugs=0 AND (tdate_debut BETWEEN :start AND :end ) ", nativeQuery = true)
    int getSumNotBugsTask(@Param("p_reference") String p_reference, @Param("start") Date start, @Param("end") Date end);

}