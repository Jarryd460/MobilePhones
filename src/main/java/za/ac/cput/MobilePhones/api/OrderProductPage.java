package za.ac.cput.MobilePhones.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.MobilePhones.domain.OrderProduct;
import za.ac.cput.MobilePhones.services.OrderProductService;

import java.util.List;

/**
 * Created by Matt on 2015/10/25.
 */

@RestController
@RequestMapping(value="/ordpro/**")
public class OrderProductPage {

    @Autowired
    private OrderProductService service;

    @RequestMapping(value = "/orderProducts/", method = RequestMethod.GET)
    public ResponseEntity<List<OrderProduct>> listAllOrderProducts() {
        List<OrderProduct> orderProductList = service.findAll();
        if(orderProductList.isEmpty()){
            return new ResponseEntity<List<OrderProduct>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<OrderProduct>>(orderProductList, HttpStatus.OK);
    }

    @RequestMapping(value = "/orderProduct/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderProduct> getOrderProduct(@PathVariable("id") long id) {
        System.out.println("Fetching OrderProduct with id " + id);
        OrderProduct orderProduct = service.findById(id);
        if (orderProduct == null) {
            System.out.println("OrderProduct with id " + id + " not found");
            return new ResponseEntity<OrderProduct>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<OrderProduct>(orderProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/orderProduct/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createOrderProduct(@RequestBody OrderProduct orderProduct, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating OrderProduct " + orderProduct.getId());

        service.create(orderProduct);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/orderProduct/{id}").buildAndExpand(orderProduct.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/orderProduct/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderProduct> updateOrderProduct(@PathVariable("id") long id, @RequestBody OrderProduct orderProduct) {
        System.out.println("Updating OrderProduct " + id);

        OrderProduct currentOrderProduct = service.findById(id);

        if (currentOrderProduct==null) {
            System.out.println("OrderProduct with id " + id + " not found");
            return new ResponseEntity<OrderProduct>(HttpStatus.NOT_FOUND);
        }

        OrderProduct updatedOrderProduct = new OrderProduct
                .Builder(currentOrderProduct.getQuantity())
                .copy(currentOrderProduct)
                .quantity(orderProduct.getQuantity())
                .build();
        service.edit(updatedOrderProduct);
        return new ResponseEntity<OrderProduct>(updatedOrderProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/orderProduct/delete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderProduct> deleteOrderProduct(@PathVariable("id") long id, @RequestBody OrderProduct ord) {
        System.out.println("Fetching & Deleting OrderProduct with id " + id);

        OrderProduct orderProduct = service.findById(id);
        if (orderProduct == null) {
            System.out.println("Unable to delete. OrderProduct with id " + id + " not found");
            return new ResponseEntity<OrderProduct>(HttpStatus.NOT_FOUND);
        }

        service.delete(orderProduct);
        return new ResponseEntity<OrderProduct>(HttpStatus.OK);
    }

}
