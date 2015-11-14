package za.ac.cput.MobilePhones.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.MobilePhones.domain.Product;
import za.ac.cput.MobilePhones.services.ProductService;

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
                .picture(product.getPicture())
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

}
