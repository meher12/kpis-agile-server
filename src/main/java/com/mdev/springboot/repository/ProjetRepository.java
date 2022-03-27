package com.mdev.springboot.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long>{
    

    List<Projet> findByTitre(String titre);
    
    Optional<Projet> findBypReference(String pReference);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE projets SET pupdated_date = (SELECT now()), totalsp_commitment = (SELECT SUM(sp.work_commitment) FROM sprints sp WHERE projets.id = sp.projet_id),"
            + "totalsp_completed = (SELECT SUM(sp.work_completed) FROM sprints sp WHERE projets.id = sp.projet_id)", nativeQuery = true)
    void  totalSpInProject();

}
