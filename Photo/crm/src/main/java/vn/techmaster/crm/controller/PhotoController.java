package vn.techmaster.crm.controller;

// import java.util.Arrays;
import java.util.List;
// import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vn.techmaster.crm.exception.UploadResponseMessage;
import vn.techmaster.crm.model.Photo;
import vn.techmaster.crm.service.PhotoService;

@RestController
@RequestMapping(value = "/photo")
public class PhotoController {
  @Autowired
  private PhotoService photoService;
  
  @GetMapping
  public ResponseEntity<List<Photo>> getCustomers(){
    return ResponseEntity
      .ok()
      .header("onemount", "specialvalue")
      .body(photoService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Photo> getCustomerById(@PathVariable("id") long id){
    return ResponseEntity
      .ok()
      .body(photoService.findById(id));
  }

  @Autowired
  public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
  }

  @PostMapping
  public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws FileUploadException {
        photoService.save(file);

        // try {
        //   return ResponseEntity.created(new URI("/photo/" + newCustomer.getId())).body(newCustomer);
        // } catch (URISyntaxException e) {
        //   //log.error(e.getMessage());
        //   return ResponseEntity.ok().body(newCustomer);      
        // }  
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
  }

  // @PostMapping("/uploadFile")
  //   public Photo uploadFile(@RequestParam("file") MultipartFile file) {
  //       String fileName = photoService.storeFile(file);

  //       String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
  //               .path("/downloadFile/")
  //               .path(fileName)
  //               .toUriString();

  //       return new Photo(fileName, fileDownloadUri,
  //               file.getContentType(), file.getSize());
  //   }

  //   @PostMapping("/uploadMultipleFiles")
  //   public List<Photo> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
  //       return Arrays.asList(files)
  //               .stream()
  //               .map(file -> uploadFile(file))
  //               .collect(Collectors.toList());
    // }

  

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Long> deleteCustomerById(@PathVariable long id) {
    photoService.deleteById(id);
    return ResponseEntity.ok(id);
  }
}
