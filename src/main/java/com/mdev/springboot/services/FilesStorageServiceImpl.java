package com.mdev.springboot.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");

  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file) {
      
      try {
          boolean result = Files.deleteIfExists(Paths.get("upload/"+file));
          if (result) {
              System.out.println("File is deleted!");
          } else {
              System.out.println("Sorry, unable to delete the file.");
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      
//      try {
//          Files.deleteIfExists(Paths.get("upload/"+file));
//          //System.out.println("upload/"+file);
//      }
//      catch (NoSuchFileException e) {
//          System.out.println(
//              "No such file/directory exists");
//      }
//      catch (DirectoryNotEmptyException e) {
//          System.out.println("Directory is not empty.");
//      }
//      catch (IOException e) {
//          System.out.println("Invalid permissions.");
//      }
//
//      System.out.println("Deletion successful.");
      
    try {
        
      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

}
