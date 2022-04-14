package com.mdev.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
