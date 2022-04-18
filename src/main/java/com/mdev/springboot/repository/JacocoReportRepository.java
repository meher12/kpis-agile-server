package com.mdev.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdev.springboot.models.JacocoReport;

@Repository
public interface JacocoReportRepository extends JpaRepository<JacocoReport, Long>{

    Optional<List<JacocoReport>> findByProjectname(String projectname);
}
