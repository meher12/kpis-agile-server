package com.mdev.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long>{
    
    List<Projet> findByUniqueID(String uniqueID);
    List<Projet> findByTitre(String titre);

}
