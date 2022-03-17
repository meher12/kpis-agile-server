package com.mdev.springboot.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
    @Query(value = "UPDATE sprints SET work_commitment = (SELECT SUM(st.story_point) FROM story st WHERE sprints.id = st.sprint_id) "
            + ", work_completed = (SELECT SUM(st.sp_completed) FROM story st WHERE sprints.id = st.sprint_id)", nativeQuery = true)
    void  sprintStoryPointUpdate();
    
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE sprints SET work_completed = (SELECT SUM(st.sp_completed) FROM story st WHERE sprints.id = st.sprint_id)", nativeQuery = true)
//    void  sprintWorkCompleted();
    
    @Query(value = "select count(*) FROM sprints", nativeQuery = true)
    int sizeofSprintTable();
    
//    @Transactional
//    @Modifying
//    @Query(value="CREATE or REPLACE VIEW velocity AS SELECT stitre, work_commitment, work_completed FROM sprints", nativeQuery = true)
//    void  createViewVelocity();
//    
//    
//    @Query(value = "SELECT * FROM velocity", nativeQuery = true)
//    List<Sprint> findAll();

}
