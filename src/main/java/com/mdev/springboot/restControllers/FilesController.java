package com.mdev.springboot.restControllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.mdev.springboot.exception.ApiResourceNotFoundException;
import com.mdev.springboot.models.FileInfo;
import com.mdev.springboot.repository.JacocoReportRepository;
import com.mdev.springboot.services.FilesStorageServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FilesController {

    @Autowired
    private FilesStorageServiceImpl storageService;
    
    @Autowired
    JacocoReportRepository reportRepository;


    @PostMapping("/upload")
    public ResponseEntity<ApiResourceNotFoundException> uploadFile(@RequestParam("file") MultipartFile file) {
      String message = "";
      try {
        storageService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResourceNotFoundException(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ApiResourceNotFoundException(message));
      }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
      List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
        String filename = path.getFileName().toString();
        String url = MvcUriComponentsBuilder
            .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

        return new FileInfo(filename, url);
      }).collect(Collectors.toList());

      return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
      Resource file = storageService.load(filename);
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
    
    // delete file both from directory and db
    @DeleteMapping("/files/upload/{filename}/{reportName}")
    public ResponseEntity<Map<String, Boolean>> deleteFileByName(@PathVariable("filename") String filename, @PathVariable("reportName") String reportName) throws IOException {

       
        Path root = Paths.get("uploads/" + filename);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        
        boolean fileDeleted = Files.deleteIfExists(root);

        if (fileDeleted) {

            reportRepository.deleteAllByProjectname(reportName);
            response.put("File Deleted", Boolean.TRUE);
        } else {
            response.put("File does not exist", Boolean.TRUE);
        }
        return ResponseEntity.ok(response);

    } 
    
    @PutMapping("/files/rename/{filename}")
    public ResponseEntity<?> renameFile(@PathVariable("filename") String filename, @RequestBody String newfilename){
        //Path file = Paths.get("uploads/" + filename);
        File file = new File("uploads/" + filename);
        boolean fileRename = file.renameTo(new File("uploads/" + newfilename));
        if (fileRename) {
         //System.out.println("File rename successful");
            return ResponseEntity.ok("File rename successful");
        } else {
           // System.out.println("File reanme failed");
            return ResponseEntity.badRequest().body("File reanme failed");
        }
       // return new ResponseEntity<>("ok", HttpStatus.OK);
    }
  }