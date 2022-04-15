package com.mdev.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    
   Optional<FileDB> findByName(String name);
   
   boolean existsByName(String filename);

}
