package za.ac.cput.MobilePhones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.MobilePhones.App;
import za.ac.cput.MobilePhones.conf.factory.ProductPriceFactory;
import za.ac.cput.MobilePhones.domain.ProductPrice;
import za.ac.cput.MobilePhones.repository.ProductPriceRepository;

import java.math.BigDecimal;
import java.util.List;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class ProductPriceServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductPriceService service;
    private Long id;
    @Autowired
    private ProductPriceRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        ProductPrice productPrice = ProductPriceFactory.createProductPrice("2015-09-23", new BigDecimal(11000));
        service.create(productPrice);
        id = productPrice.getId();
        Assert.assertNotNull(productPrice);
    }

    @Test(dependsOnMethods = "create")
    public void testGetProductPrice() throws Exception {
        ProductPrice productPrice = service.findById(id);
        Assert.assertEquals(productPrice.getDateFrom(), "2015-09-23");
    }

    @Test(dependsOnMethods = "testGetProductPrice")
    public void testGetProductPrices() throws Exception {
        List<ProductPrice> productPriceList = service.findAll();
        Assert.assertEquals(productPriceList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetProductPrices")
    public void testEditProductPrice() throws Exception {
        ProductPrice productPrice = repository.findOne(id);
        ProductPrice updatedProductPrice = new ProductPrice.Builder(productPrice.getProductPrice()).copy(productPrice).dateFrom("2015-09-25").build();
        service.edit(updatedProductPrice);
        ProductPrice newProductPrice = repository.findOne(id);
        Assert.assertEquals(newProductPrice.getDateFrom(), "2015-09-25");
    }

    @Test(dependsOnMethods = "testEditProductPrice")
    public void testDeleteProductPrice() throws Exception {
        ProductPrice productPrice = repository.findOne(id);
        service.delete(productPrice);
        ProductPrice newProductPrice = repository.findOne(id);
        Assert.assertNull(newProductPrice);
    }

}
