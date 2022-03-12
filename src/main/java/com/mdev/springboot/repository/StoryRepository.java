package com.mdev.springboot.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findBySprintId(Long sprintId);

    Optional<Story> findBystReference(String stReference);

    @Transactional
    void deleteBySprintId(Long sprintId);

}
