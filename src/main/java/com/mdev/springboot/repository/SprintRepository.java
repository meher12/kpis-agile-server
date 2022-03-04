package com.mdev.springboot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{
    
   List<Sprint> findByProjetId(Long projetId);
   
   @Transactional
   void deleteByProjetId(Long projetId);

}
