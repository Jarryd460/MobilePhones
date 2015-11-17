package za.ac.cput.MobilePhones.conf.factory;

import za.ac.cput.MobilePhones.domain.OrderProduct;
import za.ac.cput.MobilePhones.domain.Product;
import za.ac.cput.MobilePhones.domain.ProductPrice;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by student on 2015/10/18.
 */
public class ProductFactory {

    public static Product createProduct(String name, String manufacturer, BigDecimal price, String operatingSystem, String screenSize, String touchScreen, String camera, String memory, List<OrderProduct> orderProductList, List<ProductPrice> productPriceList, String pictureExtension, byte[] picture) {
        return new Product.Builder(name).manufacturer(manufacturer).price(price).operatingSystem(operatingSystem).screenSize(screenSize).touchScreen(touchScreen).camera(camera).memory(memory).orderProductList(orderProductList).productPriceList(productPriceList).pictureExtension(pictureExtension).picture(picture).build();
    }

}
