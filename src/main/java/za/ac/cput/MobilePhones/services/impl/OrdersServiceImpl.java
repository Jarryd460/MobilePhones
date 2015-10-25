package za.ac.cput.MobilePhones.services.impl;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import za.ac.cput.MobilePhones.domain.OrderProduct;
        import za.ac.cput.MobilePhones.domain.Orders;
        import za.ac.cput.MobilePhones.repository.OrdersRepository;
        import za.ac.cput.MobilePhones.services.OrdersService;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Kristen on 25/10/2015.
 */

@Service
public class OrdersServiceImpl implements OrdersService
{
    @Autowired
    OrdersRepository repository;

    @Override
    public List<OrderProduct> getOrderProducts(Long id)
    {
        return repository.findOne(id).getOrderProductList();
    }

    @Override
    public Orders findById(Long id)
    {
        return repository.findOne(id);
    }

    @Override
    public Orders create(Orders order)
    {
        return repository.save(order);
    }

    @Override
    public Orders edit(Orders order)
    {
        return repository.save(order);
    }

    @Override
    public void delete(Orders order)
    {
        repository.delete(order);
    }

    @Override
    public List<Orders> findAll()
    {
        List<Orders> allOrders = new ArrayList<Orders>();

        Iterable<Orders> orders = repository.findAll();
        for (Orders order : orders) {
            allOrders.add(order);
        }
        return allOrders;
    }
}

