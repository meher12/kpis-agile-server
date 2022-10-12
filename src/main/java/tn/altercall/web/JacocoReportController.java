package tn.altercall.web;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.altercall.entities.Sprint;
import tn.altercall.exception.ApiResourceNotFoundException;
import tn.altercall.exception.ResourceNotFoundException;
import tn.altercall.entities.JacocoReport;
import tn.altercall.payload.response.MessageResponse;
import tn.altercall.repository.JacocoReportRepository;
import tn.altercall.repository.ProjetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@Slf4j
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
    // get List report by projectReference
    @GetMapping("/reports/{pReference}")
    public ResponseEntity<List<JacocoReport>> getAllReportByProjectReference(@PathVariable("pReference") String pReference) {
        var result = jacocoReportRepository.findByProjectRef(pReference)
                .orElseThrow(()-> new ResourceNotFoundException("Not found report with reference : " + pReference));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // create Sprint
    @PostMapping("/reports/add")
    public ResponseEntity<?> addXmlReport(@RequestBody ArrayList<JacocoReport> reportRequest) {

        List<JacocoReport> jacocoReportList = new ArrayList<>();
        JacocoReport _jacocoReport = new JacocoReport();
        float sum = 0;
        float totalcoverage = 0;
        for (JacocoReport jacocoReport : reportRequest) {
            sum += jacocoReport.getPercentage();
        }

        totalcoverage = sum / 6;

        for (JacocoReport jacocoReport2 : reportRequest) {

            //jacocoReport2.setTotalpercentage(Float.parseFloat(String.format("%.2f", totalcoverage)));
            jacocoReport2.setTotalpercentage(totalcoverage);
            jacocoReport2.setCreatedAt(new Date());

            if (!jacocoReportRepository.existsByProjectname(jacocoReport2.getProjectname())) {
               // log.info("-------- ------------------ ******************** {}",jacocoReport2.getProjectRef());
                jacocoReportList.add(new JacocoReport(jacocoReport2.getType(), jacocoReport2.getProjectname(),
                        jacocoReport2.getCovered(), jacocoReport2.getMissed(), jacocoReport2.getPercentage(),
                        jacocoReport2.getTotalpercentage(), jacocoReport2.getCreatedAt(), jacocoReport2.getProjectRef()));
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

    @DeleteMapping("/report/deleteallbyname/{reportName}")
    public ResponseEntity<?> deleteAllReportByName(@PathVariable("reportName") String reportName){
        if(!jacocoReportRepository.existsByProjectname(reportName)) {
            throw new ResourceNotFoundException("Not found Report with Name = " + reportName);
        }

        jacocoReportRepository.deleteAllByProjectname(reportName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}