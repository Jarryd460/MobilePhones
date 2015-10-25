package za.ac.cput.MobilePhones.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.MobilePhones.domain.ProductPrice;
import za.ac.cput.MobilePhones.repository.ProductPriceRepository;
import za.ac.cput.MobilePhones.services.ProductPriceService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    ProductPriceRepository repository;

    @Override
    public ProductPrice findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public ProductPrice create(ProductPrice productPrice) {
        return repository.save(productPrice);
    }

    @Override
    public ProductPrice edit(ProductPrice productPrice) {
        return repository.save(productPrice);
    }

    @Override
    public void delete(ProductPrice productPrice) {
        repository.delete(productPrice);
    }

    @Override
    public List<ProductPrice> findAll() {
        List<ProductPrice> allProductPrices = new ArrayList<ProductPrice>();

        Iterable<ProductPrice> productPrices = repository.findAll();
        for (ProductPrice productPrice : productPrices) {
            allProductPrices.add(productPrice);
        }
        return allProductPrices;
    }
}
