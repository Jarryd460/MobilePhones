package za.ac.cput.MobilePhones.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.MobilePhones.domain.Customer;
import za.ac.cput.MobilePhones.domain.Orders;
import za.ac.cput.MobilePhones.services.CustomerService;

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
                .sex(customer.getSex())
                .dateOfBirth(customer.getDateOfBirth())
                .contact(customer.getContact())
                .address(customer.getAddress())
                .orderList(customer.getOrderList())
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

}
