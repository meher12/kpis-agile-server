package com.mdev.springboot.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    List<Sprint> findByProjetId(Long projetId);

    Optional<Sprint> findBysReference(String sReference);

    @Transactional
    void deleteAllByProjetId(Long projetId);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE sprints SET supdated_date = (SELECT now()), work_commitment = (SELECT COALESCE(SUM(st.story_point),0) FROM story st WHERE sprints.id = st.sprint_id) "
            + ", work_completed = (SELECT COALESCE(SUM(st.sp_completed),0) FROM story st WHERE sprints.id = st.sprint_id)", nativeQuery = true)
    void  sprintStoryPointUpdate();
    
    
    //"update author set last_name= :lastName where first_name = :firstName"
    //"INSERT INTO days_sprints(sprints_id, array_of_days) VALUES(?1, ?2);"
    //INSERT INTO array_of_days (id, daysarray) VALUES (53, '{20-02-2022}') ON CONFLICT (id, daysarray) DO UPDATE SET id = 53, daysarray = '{20-02-2022}'
    // insert number of days
    @Transactional
    @Modifying
    @Query(value="INSERT INTO days_sprints (sprint_id, days_array) VALUES (:sprint_id, ARRAY[:daysarray])", nativeQuery = true)
    void  sprintArrayDays(Long sprint_id,  List<String>  daysarray);
    
    // insert ideal line
    @Transactional
    @Modifying
    @Query(value="INSERT INTO ideall_sprints (sprint_id, il_array) VALUES (?1, ARRAY[?2])", nativeQuery = true)
    void  sprintArrayOfIdealLine(Long sprint_id,  List<String>  idealLinearray);
    
    // insert worked line
    @Transactional
    @Modifying
    @Query(value="INSERT INTO workedl_sprints (sprint_id, workedl_array) VALUES (?1, ARRAY[?2])", nativeQuery = true)
    void  sprintArrayOfWorkedLine(Long sprint_id,  List<String>  workedl_array);
    
    // select stroy points completed in project
    @Query(value = "select sp.work_completed as sp_worked from sprints sp INNER JOIN projets ON sp.projet_id=projets.id;", nativeQuery = true)
    List<String>  getLisSpCompleted();

    
//    @Transactional
//    @Modifying
//    @Query(value="CREATE or REPLACE VIEW velocity AS SELECT stitre, work_commitment, work_completed FROM sprints", nativeQuery = true)
//    void  createViewVelocity();
//    
//    
//    @Query(value = "SELECT * FROM velocity", nativeQuery = true)
//    List<Sprint> findAll();

}
