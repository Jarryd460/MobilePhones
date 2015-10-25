package za.ac.cput.MobilePhones.conf.factory;

import za.ac.cput.MobilePhones.domain.OrderProduct;

public class OrderProductFactory {

    public static OrderProduct createOrderProduct(int quantity) {
        return new OrderProduct.Builder(quantity).build();
    }

}
