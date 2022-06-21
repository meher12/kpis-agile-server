package com.mdev.springboot.restControllers.crud;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdev.springboot.exception.ApiResourceNotFoundException;
import com.mdev.springboot.models.JacocoReport;
import com.mdev.springboot.payload.response.MessageResponse;
import com.mdev.springboot.repository.JacocoReportRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class JacocoReportController {

    @Autowired
    JacocoReportRepository jacocoReportRepository;

    // get All Reports
    @GetMapping("/reports/jcoverages")
    public ResponseEntity<List<JacocoReport>> getAllJacocoReport() {
        List<JacocoReport> jacocoReport = jacocoReportRepository.findAll();

        return new ResponseEntity<>(jacocoReport, HttpStatus.OK);
    }

    @GetMapping("/reports/jcoverages/{projectname}")
    public ResponseEntity<List<JacocoReport>> getAllJacocoReportbyName(
            @PathVariable("projectname") String projectname) {

        List<JacocoReport> jacocoReports = jacocoReportRepository.findByProjectname(projectname)
                .orElseThrow(() -> new ApiResourceNotFoundException("Not found report with name : " + projectname));

        return new ResponseEntity<>(jacocoReports, HttpStatus.OK);
    }

    // create Sprint
    @PostMapping("/reports/add")
    public ResponseEntity<?> addXmlReport(@RequestBody ArrayList<JacocoReport> reportRequest) {

        List<JacocoReport> jacocoReportList = new ArrayList<JacocoReport>();
        JacocoReport _jacocoReport = new JacocoReport();
        float sum = 0;
        float totalcoverage = 0;
        for (JacocoReport jacocoReport : reportRequest) {
            sum += jacocoReport.getPercentage();
        }

        totalcoverage = sum / 6;

        for (JacocoReport jacocoReport2 : reportRequest) {

            jacocoReport2.setTotalpercentage(Float.parseFloat(String.format("%.2f", totalcoverage)));
            jacocoReport2.setCreatedAt(new Date());

            if (!jacocoReportRepository.existsByProjectname(jacocoReport2.getProjectname())) {

                jacocoReportList.add(new JacocoReport(jacocoReport2.getType(), jacocoReport2.getProjectname(),
                        jacocoReport2.getCovered(), jacocoReport2.getMissed(), jacocoReport2.getPercentage(),
                        jacocoReport2.getTotalpercentage(), jacocoReport2.getCreatedAt()));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Sorry, this content already saved !"));
            }
        }

        jacocoReportList = jacocoReportRepository.saveAll(jacocoReportList);

        return new ResponseEntity<>(jacocoReportList, HttpStatus.CREATED);
    }

    // get total jacoco coverage in demi-cercle 
    @GetMapping("/reports/projectcoverage/{projectname}")
    public ResponseEntity<Float> getTotalJacocoCoverage(@PathVariable("projectname") String projectname) {
        float result = jacocoReportRepository.getTotalcoverage(projectname);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
