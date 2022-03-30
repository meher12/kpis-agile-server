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
public interface ProjetRepository extends JpaRepository<Projet, Long> {

    List<Projet> findByTitre(String titre);

    Optional<Projet> findBypReference(String pReference);

    @Transactional
    @Modifying
    @Query(value = "UPDATE projets SET pupdated_date = (SELECT now()), totalsp_commitment = (SELECT COALESCE(SUM(sp.work_commitment),0) FROM sprints sp WHERE projets.id = sp.projet_id),"
            + "totalsp_completed = (SELECT COALESCE(SUM(sp.work_completed),0) FROM sprints sp WHERE projets.id = sp.projet_id)", nativeQuery = true)
    void totalSpInProject();
    
    
    @Transactional
    @Modifying
    @Query(value="INSERT INTO projet_sp_commitment (projet_id, project_sp_commitment) VALUES (:projet_id, ARRAY[:project_sp_commitment])", nativeQuery = true)
    void  spCommitmentArray(Long projet_id,  List<String>  project_sp_commitment);
    
    @Transactional
    @Modifying
    @Query(value="INSERT INTO projet_percentage_spc (projet_id, percentage_spc) VALUES (:projet_id, ARRAY[:percentage_spc])", nativeQuery = true)
    void  percentageSpcArray(Long projet_id,  List<String>  percentage_spc);

}

   
