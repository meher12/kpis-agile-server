package com.mdev.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

}
