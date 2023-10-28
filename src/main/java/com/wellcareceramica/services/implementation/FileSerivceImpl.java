package com.wellcareceramica.services.implementation;

import com.wellcareceramica.entities.Product;
import com.wellcareceramica.excepeions.BadApiRequest;
import com.wellcareceramica.repositories.ProductRepositories;
import com.wellcareceramica.services.FileService;
import com.wellcareceramica.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileSerivceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileSerivceImpl.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepositories productRepositories;

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {

        String originalFileName = file.getOriginalFilename();
        logger.info("FIle Name: {}",originalFileName);
        String filename = UUID.randomUUID().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExetension = filename+extension;
        String fullPathWithFilename = path+fileNameWithExetension;

        if(extension.equalsIgnoreCase(".png")||extension.equalsIgnoreCase(".jpg")||extension.equalsIgnoreCase(".jpeg")||extension.equalsIgnoreCase(".pdf")){
            //saving File
            File folder = new File(path);
            if(!folder.exists()){
                //create folder
                folder.mkdirs();
            }

            //upload file
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFilename));
            return fileNameWithExetension;

        }else {
            throw new BadApiRequest("File With this "+extension+" format not allowe");
        }
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {

        String fullPath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;
    }

    @Override
    public Boolean deleteFile(String path, String productImageName) throws FileNotFoundException {
        String fullPath = path + File.separator + productImageName;
        try {
            Path paths = Paths.get(fullPath);
            Files.delete(paths);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product updateFIle(MultipartFile file, String path,Integer productId) {

        Product product = productService.getSingleProduct(productId);
        try {
            deleteFile(path,product.getImageId());
            String imageName = uploadFile(file, path);
            product.setImageId(imageName);
            Product save = productRepositories.save(product);
            return save;
        } catch (IOException e) {
            return null;
        }
    }
}

