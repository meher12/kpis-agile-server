package tn.altercall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.altercall.entities.JacocoReport;

@Repository
public interface JacocoReportRepository extends JpaRepository<JacocoReport, Long> {

    Optional<List<JacocoReport>> findByProjectname(String projectname);

    boolean existsByProjectname(String projectname);

    @Transactional
    void deleteAllByProjectname(String projectname);

    @Query(value = "select totalpercentage from jcoverage where projectname=:projectname group by totalpercentage", nativeQuery = true)
    float getTotalcoverage(@Param("projectname") String projectname);

}
