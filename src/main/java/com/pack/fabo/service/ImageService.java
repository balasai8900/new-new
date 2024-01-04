package com.pack.fabo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pack.fabo.entity.ImageEntity;

public interface ImageService {
    void storeImage(MultipartFile file, String category);
    List<byte[]> getImagesByCategory(String category); 
    // Add more methods as needed
}