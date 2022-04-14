package com.mdev.springboot.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.mdev.springboot.models.FileDB;

public interface FilesStorageService {
    
  public void init();

 // public void save(MultipartFile file);
  
  public FileDB store(MultipartFile file) throws IOException;

  public Resource load(String filename);

  public void deleteAll();

  public Stream<Path> loadAll();
}
