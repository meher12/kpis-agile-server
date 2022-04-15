package com.mdev.springboot.restControllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mdev.springboot.exception.ApiResourceNotFoundException;
import com.mdev.springboot.exception.ResourceNotFoundException;
import com.mdev.springboot.models.FileDB;
import com.mdev.springboot.payload.response.ResponseFile;
import com.mdev.springboot.repository.FileDBRepository;
import com.mdev.springboot.services.FilesStorageServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FilesController {

    @Autowired
    private FilesStorageServiceImpl storageService;

    @Autowired
    private FileDBRepository fileDBRepository;

    @PostMapping("/upload")
    public ResponseEntity<ApiResourceNotFoundException> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResourceNotFoundException(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ApiResourceNotFoundException(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("api/files/")
                    .path(dbFile.getId()).toUriString();

            return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFileByName(@PathVariable String id) {

        FileDB filedb = this.fileDBRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found file with id: " + id));
        this.fileDBRepository.delete(filedb);

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("File Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

    // delete file both from directory and db
    @DeleteMapping("/files/upload/{filename}/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFileByfromdisc(@PathVariable String filename,
            @PathVariable String id) throws IOException {

        FileDB filedb = this.fileDBRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found file with id: " + id));

        this.fileDBRepository.delete(filedb);

        Path root = Paths.get("uploads/" + filename);
        boolean fileDeleted = Files.deleteIfExists(root);

        Map<String, Boolean> response = new HashMap<String, Boolean>();

        if (fileDeleted) {

            response.put("File Deleted from disc", Boolean.TRUE);
        } else {
            response.put("File does not exist", Boolean.TRUE);
        }
        return ResponseEntity.ok(response);

    }
}