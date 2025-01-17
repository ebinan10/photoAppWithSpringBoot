package com.javaApp.springJava2.service;

import com.javaApp.springJava2.model.Photo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotoService {
    private final Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg",null));
    }};

    public Collection<Photo> values() {

        return db.values();
    }

    public Map<String, Photo> getDb() {
        return db;
    }

    public Photo get(String id) {
        return db.get(id);
    }

    public Photo remove(String id) {
        return db.remove(id);
    }


    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        photo.setData(data);
        db.put(photo.getId(), photo);
        return photo;
    }
}
