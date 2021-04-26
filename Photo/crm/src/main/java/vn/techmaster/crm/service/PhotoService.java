package vn.techmaster.crm.service;

// import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

// import org.springframework.util.StringUtils;

import vn.techmaster.crm.exception.PhotoException;
import vn.techmaster.crm.model.Photo;

import vn.techmaster.crm.repository.PhotoRepository;

@Service
public class PhotoService {
  @Autowired
  private PhotoRepository photoRepository;
  // private final Path fileStorageLocation;

  static final String PHOTO_ID_NOT_EXIST = "Photo id %d doest not exist";

  public List<Photo> getAll(){
    return photoRepository.findAll();
  }


  public Photo findById(long id){
    Optional<Photo> photo = photoRepository.findById(id);
    if (photo.isPresent()) {
      return photo.get();
    } else {
      throw new PhotoException("Cannot find photo",
        new Throwable(String.format(PHOTO_ID_NOT_EXIST, id)), 
        HttpStatus.NOT_FOUND);      
    }    
  }

  @Value("${upload.path}")
    private String uploadPath;

    public void save(MultipartFile file) throws FileUploadException {
        try {
            Path root = Paths.get(uploadPath);
            Path resolve = root.resolve(file.getOriginalFilename());
            if (resolve.toFile()
                       .exists()) {
                throw new FileUploadException("File already exists: " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
        }
    }


  public long deleteById(long id) {
    Optional<Photo> optionalPhoto = photoRepository.findById(id);
    if (optionalPhoto.isPresent()) {      
     photoRepository.delete(optionalPhoto.get());
      return id;
    } else {
      throw new PhotoException("Cannot delete a photo", 
      new Throwable(String.format(PHOTO_ID_NOT_EXIST, id)), 
      HttpStatus.NOT_FOUND);
    }
  }
}
