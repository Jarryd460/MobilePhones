package za.ac.cput.MobilePhones.services;

import za.ac.cput.MobilePhones.domain.Customer;
import za.ac.cput.MobilePhones.domain.Orders;

import java.util.List;

/**
 * Created by student on 2015/10/18.
 */
public interface CustomerService extends Services<Customer, Long>{

    List<Orders> getOrders(Long id);

}
