package tn.altercall.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.altercall.entities.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findBySprintId(Long sprintId);

    Optional<Story> findBystReference(String stReference);

    @Transactional
    void deleteAllBySprintId(Long sprintId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE story SET stupdated_date = (SELECT now()), story_point = (SELECT COALESCE(SUM(ts.estimation),0) FROM tasks ts WHERE (story.id = ts.story_id AND (ts.status = 'Scheduled' OR ts.status = 'In_progress'))) "
            + ", sp_completed = (SELECT COALESCE(SUM(ts.estimation),0) FROM tasks ts WHERE (story.id = ts.story_id AND (ts.status = 'Completed' OR ts.status = 'Succeeded')))", nativeQuery = true)
    void StoryPointUpdate();

    @Transactional
    @Modifying
    @Query(value = "update story SET stupdated_date = (SELECT now()), plus_sp = (SELECT COALESCE(SUM(ts.estimation),0) FROM tasks ts WHERE story.id = ts.story_id AND ts.type_task='More_task')", nativeQuery = true)
    void updatePlusSp();

}
