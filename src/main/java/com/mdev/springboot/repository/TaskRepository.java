package com.mdev.springboot.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStoryId(Long storyId);

    Optional<Task> findBytReference(String tReference);

    @Transactional
    void deleteAllByStoryId(Long storyId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tasks SET tsupdated_date = (SELECT now()) FROM tasks ts WHERE TRUE", nativeQuery = true)
    void tasktimeUpdate();

    @Query(value = "select ts.estimation from tasks ts where ts.type_task='More_task'", nativeQuery = true)
    ArrayList<String> getspMoretasks();
}
