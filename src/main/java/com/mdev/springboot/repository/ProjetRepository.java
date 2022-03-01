package com.mdev.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long>{

}
