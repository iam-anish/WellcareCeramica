package com.wellcareceramica.controllers;

import com.wellcareceramica.entities.ImageResponse;
import com.wellcareceramica.entities.PageableResponse;
import com.wellcareceramica.entities.Product;
import com.wellcareceramica.repositories.ProductRepositories;
import com.wellcareceramica.services.FileService;
import com.wellcareceramica.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepositories productRepositories;

    @Autowired
    private FileService fileService;

    @Value("${product.image.path}")
    private String imagePath;

    @PostMapping("/add/{creatorId}")
    public ResponseEntity<Product> addProduct(@RequestBody Product product,@PathVariable Integer creatorId) {
        Product returnMessage = productService.addProduct(product,creatorId);
        return new ResponseEntity<>(returnMessage, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<PageableResponse<Product>> getProduct(
            @RequestParam(name = "categorySysid", defaultValue = "", required = false) Integer categorySysid,
            @RequestParam(name = "typeSysid", defaultValue = "", required = false) Integer typeSysid,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ) {
        PageableResponse<Product> productList = productService.getProduct(categorySysid, typeSysid,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PutMapping("/update/{modifierId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product,@PathVariable Integer modifierId){
        Product product1 = productService.updateProduct(product,modifierId);
        return new ResponseEntity<>(product1,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}/{modifierId}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable Integer productId,@PathVariable Integer modifierId) throws FileNotFoundException {
        Product product1 = productService.getSingleProduct(productId);
        Integer returnMsg = productService.deleteProduct(productId,modifierId);
        fileService.deleteFile(imagePath,product1.getImageId());
        return new ResponseEntity<>(returnMsg,HttpStatus.OK);
    }

    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse> uploadProductImage(
            @PathVariable Integer productId,
            @RequestParam("productImage") MultipartFile image
    ) throws IOException {
        Product product = productService.getSingleProduct(productId);
        if(product.getImageId()!=null){
           Product product1 =  fileService.updateFIle(image, imagePath, productId);
            Product product2 = productRepositories.save(product1);
           ImageResponse response = ImageResponse.builder().imageName(product2.getImageId()).message("Product image is successfully updated !!").Status(HttpStatus.OK).success(true).build();
           return new ResponseEntity<>(response,HttpStatus.OK);
        }
        String fileName = fileService.uploadFile(image, imagePath);
        product.setImageId(fileName);
        Product product1 = productRepositories.save(product);

        ImageResponse response = ImageResponse.builder().imageName(product1.getImageId()).message("Product image is successfully uploaded !!").Status(HttpStatus.CREATED).success(true).build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("image/{productId}")
    public void serveUserImage(@PathVariable Integer productId, HttpServletResponse response) throws IOException {
        Product product = productService.getSingleProduct(productId);
        InputStream resource = fileService.getResource(imagePath,product.getImageId());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
