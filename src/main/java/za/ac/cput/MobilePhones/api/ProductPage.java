package za.ac.cput.MobilePhones.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.MobilePhones.domain.Product;
import za.ac.cput.MobilePhones.services.ProductService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by student on 2015/10/18.
 */

@RestController
@RequestMapping(value="/pro/**")
public class ProductPage {

    @Autowired
    private ProductService service;

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> productList = service.findAll();
        if(productList.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        System.out.println("Fetching Product with id " + id);
        Product product = service.findById(id);
        if (product == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Product " + product.getId());

        service.create(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        System.out.println("Updating Product " + id);

        Product currentProduct = service.findById(id);

        if (currentProduct==null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        Product updatedProduct = new Product
                .Builder(currentProduct.getName())
                .copy(currentProduct)
                .name(product.getName())
                .manufacturer(product.getManufacturer())
                .price(product.getPrice())
                .operatingSystem(product.getOperatingSystem())
                .screenSize(product.getScreenSize())
                .touchScreen(product.getTouchScreen())
                .camera(product.getCamera())
                .memory(product.getMemory())
                .orderProductList(product.getOrderProductList())
                .productPriceList(product.getProductPriceList())
                .build();
        service.edit(updatedProduct);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id, @RequestBody Product pro) {
        System.out.println("Fetching & Deleting Product with id " + id);

        Product product = service.findById(id);
        if (product == null) {
            System.out.println("Unable to delete. Product with id " + id + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        service.delete(product);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @RequestMapping(value = "/product/setPicture/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> setPicture(@PathVariable("id") long id, @RequestParam("pictureExtension") String pictureExtension, @RequestParam("picture") MultipartFile file) {
        Product currentProduct = service.findById(id);

        if(currentProduct == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        try {
            Product updatedProduct = new Product.Builder(currentProduct.getName())
                    .copy(currentProduct)
                    .pictureExtension(pictureExtension)
                    .picture(file.getBytes())
                    .build();

            service.edit(updatedProduct);
            return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/product/getPicture/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getPicture(@PathVariable long id) {
        Product product = service.findById(id);

        if (product == null || product.getPicture() == null) {
            return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .contentLength(product.getPicture().length)
                .contentType(MediaType.parseMediaType(product.getPictureExtension()))
                .body(new InputStreamResource(new ByteArrayInputStream(product.getPicture())));
    }

    @RequestMapping(value = "/product/removePicture/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> removePicture(@PathVariable("id") long id) {
        Product currentProduct = service.findById(id);
        if (currentProduct == null || currentProduct.getPicture() == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }

        Product updatedProduct = new Product.Builder(currentProduct.getName())
                .copy(currentProduct)
                .pictureExtension(null)
                .picture(null)
                .build();

        service.edit(updatedProduct);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

}
