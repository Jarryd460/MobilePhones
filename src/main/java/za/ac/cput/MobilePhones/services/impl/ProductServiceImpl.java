package za.ac.cput.MobilePhones.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.MobilePhones.domain.Product;
import za.ac.cput.MobilePhones.repository.ProductRepository;
import za.ac.cput.MobilePhones.services.ProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2015/10/18.
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public Product findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product edit(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> allProducts = new ArrayList<Product>();

        Iterable<Product> products = repository.findAll();
        for (Product product : products) {
            allProducts.add(product);
        }
        return allProducts;
    }

}
