package com.wellcareceramica.services.implementation;
import com.wellcareceramica.entities.PageableResponse;
import com.wellcareceramica.entities.Product;
import com.wellcareceramica.helper.Helper;
import com.wellcareceramica.repositories.ProductRepositories;
import com.wellcareceramica.services.FileService;
import com.wellcareceramica.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class ProductImpl implements ProductService {

    @Autowired
    private ProductRepositories productRepositories;

    @Value("${product.image.path}")
    private String imagePath;

    @Autowired
    private FileService fileService;

    @Override
    public Product addProduct(Product product,Integer creatorId) {

        System.out.println(product.getTypeSysid());
        System.out.println(product.getCategorySysid());
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        product.setCreatedDate(ts);
        product.setSysStatus("A");
        product.setCreatorId(creatorId);

        try {
           Product product1 = productRepositories.save(product);
           return product1;
        }
        catch (Exception e) {
            return new Product();
        }
    }

    @Override
    public Product getSingleProduct(Integer productId) {
        Product product = productRepositories.findByProductSysidAndSysStatus(productId,"A");
        return product;
    }

    @Override
    public PageableResponse<Product> getProduct(Integer categorySysid, Integer typeSysid, int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Product> page;
        if(categorySysid==null&&typeSysid==null){
           page  = productRepositories.findBySysStatus("A",pageable);
        } else if (typeSysid==null) {
            page = productRepositories.findBySysStatusAndCategorySysid("A",categorySysid,pageable);
        }
        else {
            page = productRepositories.findBySysStatusAndCategorySysidAndTypeSysid("A",categorySysid,typeSysid,pageable);
        }
        return Helper.getPageableResponse(page,Product.class);
    }

    @Override
    public PageableResponse<Product> filterProduct(String name) {

        Sort sort = Sort.by("name").descending();
        Pageable pageable = PageRequest.of(0,1000,sort);
        Page<Product> page = productRepositories.findByNameLikeAndSysStatus(name,"A",pageable);
        return Helper.getPageableResponse(page,Product.class);
    }

    @Override
    public Product updateProduct(Product product,Integer modifierId) {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());

        Product product1 = productRepositories.findByProductSysidAndSysStatus(product.getProductSysid(),"A");
        product1.setName(product.getName());
        product1.setSize(product.getSize());
        product1.setCategorySysid(product.getCategorySysid());
        product1.setModifiedDate(ts);
        product1.setModifierId(modifierId);
        product1.setTypeSysid(product.getTypeSysid());

        return productRepositories.save(product1);
    }

    @Override
    public Integer deleteProduct(Integer productId,Integer modifierId) {

        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());

        Product product = productRepositories.findByProductSysidAndSysStatus(productId,"A");
        product.setSysStatus("D");
        product.setModifiedDate(ts);
        product.setModifierId(modifierId);

        try{
            fileService.deleteFile(imagePath,product.getImageId());
            productRepositories.save(product);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
