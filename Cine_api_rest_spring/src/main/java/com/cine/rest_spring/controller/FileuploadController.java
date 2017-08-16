
package com.cine.rest_spring.controller;


import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cine.rest_spring.model.FileInfo;
import java.io.IOException;
import org.springframework.http.MediaType;

@RestController
public class FileuploadController {
 @Autowired
 ServletContext context;

 @RequestMapping(value = "/fileupload", headers=("content-type=multipart/*"), method = RequestMethod.POST)
 public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile inputFile) {
  FileInfo fileInfo = new FileInfo();
  HttpHeaders headers = new HttpHeaders();
  if (!inputFile.isEmpty()) 
  {
   try {
    System.out.println("Entro");
    String originalFilename = inputFile.getOriginalFilename();
    System.out.println("Entro " + originalFilename);
    //File destinationFile = new File(context.getRealPath("\\WEB-INF\\uploaded\\")+ originalFilename);
    File destinationFile = new File("C:\\prueba\\"+originalFilename);
    System.out.println("Entro "+destinationFile.getPath());
    inputFile.transferTo(destinationFile);
    fileInfo.setFileName(destinationFile.getPath());
    fileInfo.setFileSize(inputFile.getSize());
    headers.add("File Uploaded Successfully - ", originalFilename);
    return new ResponseEntity<FileInfo>(fileInfo, headers, HttpStatus.OK);
   } catch (IOException | IllegalStateException e) {    
    return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
   }
  }else{
   return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
  }
 }
}
