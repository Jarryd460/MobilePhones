package za.ac.cput.MobilePhones.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.MobilePhones.domain.ProductPrice;
import za.ac.cput.MobilePhones.services.ProductPriceService;

import java.util.List;
@RestController
@RequestMapping(value="/propri/**")
public class ProductPricePage {

    @Autowired
    private ProductPriceService service;

    @RequestMapping(value = "/productPrices/", method = RequestMethod.GET)
    public ResponseEntity<List<ProductPrice>> listAllProductPrices() {
        List<ProductPrice> productPriceList = service.findAll();
        if(productPriceList.isEmpty()){
            return new ResponseEntity<List<ProductPrice>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ProductPrice>>(productPriceList, HttpStatus.OK);
    }

    @RequestMapping(value = "/productPrice/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductPrice> getProductPrice(@PathVariable("id") long id) {
        System.out.println("Fetching ProductPrice with id " + id);
        ProductPrice productPrice = service.findById(id);
        if (productPrice == null) {
            System.out.println("ProductPrice with id " + id + " not found");
            return new ResponseEntity<ProductPrice>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ProductPrice>(productPrice, HttpStatus.OK);
    }

    @RequestMapping(value = "/productPrice/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createProductPrice(@RequestBody ProductPrice productPrice, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating ProductPrice " + productPrice.getId());

        service.create(productPrice);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/productPrice/{id}").buildAndExpand(productPrice.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/productPrice/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductPrice> updateProductPrice(@PathVariable("id") long id, @RequestBody ProductPrice productPrice) {
        System.out.println("Updating ProductPrice " + id);

        ProductPrice currentProductPrice = service.findById(id);

        if (currentProductPrice==null) {
            System.out.println("ProductPrice with id " + id + " not found");
            return new ResponseEntity<ProductPrice>(HttpStatus.NOT_FOUND);
        }

        ProductPrice updatedProductPrice = new ProductPrice
                .Builder(currentProductPrice.getProductPrice())
                .copy(currentProductPrice)
                .dateFrom(productPrice.getDateFrom())
                .productPrice(productPrice.getProductPrice())
                .build();
        service.edit(updatedProductPrice);
        return new ResponseEntity<ProductPrice>(updatedProductPrice, HttpStatus.OK);
    }

    @RequestMapping(value = "/productPrice/delete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductPrice> deleteProductPrice(@PathVariable("id") long id, @RequestBody ProductPrice propri) {
        System.out.println("Fetching & Deleting ProductPrice with id " + id);

        ProductPrice productPrice = service.findById(id);
        if (productPrice == null) {
            System.out.println("Unable to delete. ProductPrice with id " + id + " not found");
            return new ResponseEntity<ProductPrice>(HttpStatus.NOT_FOUND);
        }

        service.delete(productPrice);
        return new ResponseEntity<ProductPrice>(HttpStatus.OK);
    }

}
