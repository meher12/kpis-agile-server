package tn.altercall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tn.altercall.utils.ViewAllReference;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ViewAllReferenceRepository extends JpaRepository<ViewAllReference, Long> {

    ViewAllReference findByrefProject(String pReference);

    @Modifying(flushAutomatically = true)
    @Transactional
    void deleteAllByrefProject(String pReference);

    /*
     * select all reference in project
     * SELECT P.p_reference, S.s_reference, ST.st_reference, T.t_reference FROM projects P JOIN sprints S ON S.projet_id = P.id JOIN story ST ON S.id = ST.sprint_id JOIN tasks T ON ST.id = T.story_id GROUP BY P.p_reference, S.s_reference, ST.st_reference, T.t_reference;
     * */

    /*
     * Select by project Reference
     *
     * SELECT P.p_reference as refproject, P.title as projetc_name, S.s_reference as refsprint, S.title as sprint_name, ST.st_reference as restory, ST.title as story_name FROM projects P JOIN sprints S ON S.projet_id = P.id JOIN story ST ON S.id = ST.sprint_id WHERE (P.p_reference='PUID1EBD9') GROUP BY P.p_reference, P.title,  S.s_reference, S.title, ST.st_reference, ST.title;
     * */

    /*
     * Select by project Reference and add task order by task date begin
     * SELECT P.p_reference as refproject, P.title as projetc_name, S.s_reference as refsprint, S.title as sprint_name, ST.st_reference as restory, ST.title as story_name, T.t_reference as taskref, T.title as task_name, T.tdate_debut as task_startedAt FROM projects P JOIN sprints S ON S.projet_id = P.id JOIN story ST ON S.id = ST.sprint_id  JOIN tasks T ON ST.id = T.story_id  WHERE (P.p_reference='PUID1EBD9') GROUP BY P.p_reference, P.title,  S.s_reference, S.title, ST.st_reference, ST.title, T.t_reference, T.title, T.tdate_debut ORDER BY T.tdate_debut;
     * */
   /* @Query(value = " insert into by_ref_view (SELECT P.p_reference as refproject, P.title as projetc_name, S.s_reference as refsprint, S.title as sprint_name, ST.st_reference as restory, ST.title as story_name, T.t_reference as taskref, T.title as task_name, T.tdate_debut as task_startedAt FROM projects P JOIN sprints S ON S.projet_id = P.id JOIN story ST ON S.id = ST.sprint_id  JOIN tasks T ON ST.id = T.story_id  WHERE (P.p_reference=:p_reference) GROUP BY P.p_reference, P.title,  S.s_reference, S.title, ST.st_reference, ST.title, T.t_reference, T.title, T.tdate_debut ORDER BY T.tdate_debut);", nativeQuery = true)
    List<ViewAllReference> getAllReferenceByProject(@Param("p_reference") String p_reference);*/



    @Modifying(flushAutomatically = true)
    @Transactional
    @Query(value = "INSERT INTO by_ref_view (id, ref_project, ref_sprint, ref_story, ref_task, task_stared_at, title_project, title_sprint, title_story, title_task) select nextval('byview_generator'), P.p_reference , S.s_reference , ST.st_reference , T.t_reference, T.tdate_debut ,  P.title , S.title , ST.title , T.title FROM projects P JOIN sprints S ON S.projet_id = P.id JOIN story ST ON S.id = ST.sprint_id  JOIN tasks T ON ST.id = T.story_id  WHERE (P.p_reference=:p_reference) ORDER BY T.tdate_debut", nativeQuery  = true)
    void insertAllReferenceByProject(@Param("p_reference") String p_reference);

    @Query(value = "select * from by_ref_view WHERE (ref_project=:ref_project);", nativeQuery = true)
    ArrayList<ViewAllReference> getAllReferenceByProject(@Param("ref_project") String ref_project);
}
