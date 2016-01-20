package za.ac.cput.MobilePhones.conf.factory;

import za.ac.cput.MobilePhones.domain.*;

import java.util.List;

/**
 * Created by student on 2015/10/18.
 */
public class CustomerFactory {

    public static Customer createCustomer(Name name, Demographic demographic, String dateOfBirth, Contact contact, Address address, List<Orders>orderList, Login login, String isAdmin, String pictureExtension, byte[] picture) {
        return new Customer.Builder(name).demographic(demographic).dateOfBirth(dateOfBirth).contact(contact).address(address).orderList(orderList).login(login).isAdmin(isAdmin).pictureExtension(pictureExtension).picture(picture).build();
    }

}
