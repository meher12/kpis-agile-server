package com.mdev.springboot.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.mdev.springboot.models.FileInfo;

public interface FilesStorageService {
    
    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);

   public void deleteAll();

    public Stream<Path> loadAll();
}
