package za.ac.cput.MobilePhones.conf.factory;

import za.ac.cput.MobilePhones.domain.*;

import java.util.List;

/**
 * Created by student on 2015/10/18.
 */
public class CustomerFactory {

    public static Customer createCustomer(Name name, String sex, String dateOfBirth, Contact contact, Address address, List<Orders>orderList) {
        return new Customer.Builder(name).sex(sex).dateOfBirth(dateOfBirth).contact(contact).address(address).orderList(orderList).build();
    }

}