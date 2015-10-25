package za.ac.cput.MobilePhones.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.MobilePhones.domain.OrderProduct;
import za.ac.cput.MobilePhones.repository.OrderProductRepository;
import za.ac.cput.MobilePhones.services.OrderProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 2015/10/25.
 */

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    OrderProductRepository repository;


    @Override
    public OrderProduct findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return repository.save(orderProduct);
    }

    @Override
    public OrderProduct edit(OrderProduct orderProduct) {
        return repository.save(orderProduct);
    }

    @Override
    public void delete(OrderProduct orderProduct) {
        repository.delete(orderProduct);
    }

    @Override
    public List<OrderProduct> findAll() {
        List<OrderProduct> allOrderProducts = new ArrayList<OrderProduct>();

        Iterable<OrderProduct> orderProducts = repository.findAll();
        for (OrderProduct orderProduct : orderProducts) {
            allOrderProducts.add(orderProduct);
        }
        return allOrderProducts;
    }
}
