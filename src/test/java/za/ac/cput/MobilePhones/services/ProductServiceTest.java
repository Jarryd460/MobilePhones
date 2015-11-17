package za.ac.cput.MobilePhones.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import za.ac.cput.MobilePhones.App;
import za.ac.cput.MobilePhones.conf.factory.ProductFactory;
import za.ac.cput.MobilePhones.domain.Product;
import za.ac.cput.MobilePhones.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by student on 2015/10/18.
 */

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class ProductServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ProductService service;
    private Long id;
    @Autowired
    private ProductRepository repository;

    @Test
    public void create() throws Exception {
        repository.deleteAll();
        Product product = ProductFactory.createProduct("Note 5", "Samsung", new BigDecimal(10500), "Android Lollipop 5.1", "120*50", "Yes", "15", "32", null, null, null, null);
        service.create(product);
        id = product.getId();
        Assert.assertNotNull(product);
    }

    @Test(dependsOnMethods = "create")
    public void testGetProduct() throws Exception {
        Product product = service.findById(id);
        Assert.assertEquals(product.getName(), "Note 5");
    }

    @Test(dependsOnMethods = "testGetProduct")
    public void testGetProducts() throws Exception {
        List<Product> productList = service.findAll();
        Assert.assertEquals(productList.size(), 1);
    }

    @Test(dependsOnMethods = "testGetProducts")
    public void testEditProduct() throws Exception {
        Product product = repository.findOne(id);
        Product updatedProduct = new Product.Builder(product.getName()).copy(product).memory("64").build();
        service.edit(updatedProduct);
        Product newProduct = repository.findOne(id);
        Assert.assertEquals(newProduct.getMemory(), "64");
    }

    @Test(dependsOnMethods = "testEditProduct")
    public void testDeleteProduct() throws Exception {
        Product product = repository.findOne(id);
        service.delete(product);
        Product newProduct = repository.findOne(id);
        Assert.assertNull(newProduct);
    }

}
