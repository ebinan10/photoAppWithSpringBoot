package com.javaApp.springJava2.web;
import com.javaApp.springJava2.model.Photo;
import com.javaApp.springJava2.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class  PhotozController {

 private final PhotoService photoService;

    public PhotozController(@Autowired PhotoService photoService) {
        this.photoService = photoService;
    }



    //    private final List<Photo> db = List.of(new Photo( "1" ,  "hello.jpg"));

        @GetMapping("/")
        public String hello(){
            return "Hello World";
        }
        @GetMapping("/photos")
    public Collection<Photo>get(){
            return photoService.values();
        }



    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id){
            Photo photo = photoService.get(id);
            if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return photo;
        }
    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id){
       Photo photo = photoService.remove(id);
       if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/photos")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {

        return photoService.save(file.getOriginalFilename(), file.getContentType() ,file.getBytes());
    }
}
