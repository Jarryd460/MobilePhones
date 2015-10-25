package za.ac.cput.MobilePhones.conf.factory;

import za.ac.cput.MobilePhones.domain.ProductPrice;

import java.math.BigDecimal;

public class ProductPriceFactory {

    public static ProductPrice createProductPrice(String dateFrom, BigDecimal productPrice) {
        return new ProductPrice.Builder(productPrice).dateFrom(dateFrom).build();
    }

}
