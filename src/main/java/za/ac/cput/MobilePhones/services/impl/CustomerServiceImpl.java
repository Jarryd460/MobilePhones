package za.ac.cput.MobilePhones.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.MobilePhones.domain.Customer;
import za.ac.cput.MobilePhones.domain.Orders;
import za.ac.cput.MobilePhones.repository.CustomerRepository;
import za.ac.cput.MobilePhones.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/10/18.
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Override
    public List<Orders> getOrders(Long id) {
        return repository.findOne(id).getOrderList();
    }

    @Override
    public Customer findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer edit(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> allCustomers = new ArrayList<Customer>();

        Iterable<Customer> customers = repository.findAll();
        for (Customer customer : customers) {
            allCustomers.add(customer);
        }
        return allCustomers;
    }

}
