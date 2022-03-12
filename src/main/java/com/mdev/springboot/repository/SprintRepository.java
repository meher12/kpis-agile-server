package com.mdev.springboot.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{
    
    List<Sprint> findByProjetId(Long projetId);
    
    Optional<Sprint> findBysReference(String sReference);
   
   @Transactional
   void deleteByProjetId(Long projetId);

}
