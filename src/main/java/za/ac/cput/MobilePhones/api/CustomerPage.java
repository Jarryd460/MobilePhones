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
import za.ac.cput.MobilePhones.domain.Customer;
import za.ac.cput.MobilePhones.domain.Orders;
import za.ac.cput.MobilePhones.services.CustomerService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by student on 2015/10/18.
 */

@RestController
@RequestMapping(value="/cus/**")
public class CustomerPage {

    @Autowired
    private CustomerService service;

    @RequestMapping(value="/orders/{id}", method= RequestMethod.GET)
    public List<Orders> getOrders(@PathVariable Long id) {
        return service.getOrders(id);
    }

    @RequestMapping(value = "/customers/", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> customerList = service.findAll();
        if(customerList.isEmpty()){
            return new ResponseEntity<List<Customer>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
        System.out.println("Fetching Customer with id " + id);
        Customer customer = service.findById(id);
        if (customer == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Customer " + customer.getId());

        service.create(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/customer/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        System.out.println("Updating Customer " + id);

        Customer currentCustomer = service.findById(id);

        if (currentCustomer==null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        Customer updatedCustomer = new Customer
                .Builder(currentCustomer.getName())
                .copy(currentCustomer)
                .name(customer.getName())
                .demographic(customer.getDemographic())
                .dateOfBirth(customer.getDateOfBirth())
                .contact(customer.getContact())
                .address(customer.getAddress())
                .orderList(customer.getOrderList())
                .login(customer.getLogin())
                .isAdmin(customer.getIsAdmin())
                .build();
        service.edit(updatedCustomer);
        return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/delete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id, @RequestBody Customer cus) {
        System.out.println("Fetching & Deleting Customer with id " + id);

        Customer customer = service.findById(id);
        if (customer == null) {
            System.out.println("Unable to delete. Customer with id " + id + " not found");
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        service.delete(customer);
        return new ResponseEntity<Customer>(HttpStatus.OK);
    }

    @RequestMapping(value = "/customer/setPicture/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> setPicture(@PathVariable("id") long id, @RequestParam("pictureExtension") String pictureExtension, @RequestParam("picture") MultipartFile file) {
        Customer currentCustomer = service.findById(id);

        if(currentCustomer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        try {
            Customer updatedCustomer = new Customer.Builder(currentCustomer.getName())
                    .copy(currentCustomer)
                    .pictureExtension(pictureExtension)
                    .picture(file.getBytes())
                    .build();

            service.edit(updatedCustomer);
            return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/customer/getPicture/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getPicture(@PathVariable long id) {
        Customer customer = service.findById(id);

        if (customer == null || customer.getPicture() == null) {
            return new ResponseEntity<InputStreamResource>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
                .contentLength(customer.getPicture().length)
                .contentType(MediaType.parseMediaType(customer.getPictureExtension()))
                .body(new InputStreamResource(new ByteArrayInputStream(customer.getPicture())));
    }

    @RequestMapping(value = "/customer/removePicture/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> removePicture(@PathVariable("id") long id) {
        Customer currentCustomer = service.findById(id);
        if (currentCustomer == null || currentCustomer.getPicture() == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }

        Customer updatedCustomer = new Customer.Builder(currentCustomer.getName())
                .copy(currentCustomer)
                .pictureExtension(null)
                .picture(null)
                .build();

        service.edit(updatedCustomer);
        return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
    }

}
