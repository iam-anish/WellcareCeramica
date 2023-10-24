package com.wellcareceramica.services;

import com.wellcareceramica.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uploadFile(MultipartFile file, String path) throws IOException;

    InputStream getResource(String path, String name) throws FileNotFoundException;

    Boolean deleteFile(String path,String productImageName) throws  FileNotFoundException;

    Product updateFIle(MultipartFile file, String path, Integer productId);
}
