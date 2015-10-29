package za.ac.cput.MobilePhones.api;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.util.UriComponentsBuilder;
        import za.ac.cput.MobilePhones.domain.OrderProduct;
        import za.ac.cput.MobilePhones.domain.Orders;
        import za.ac.cput.MobilePhones.services.OrdersService;

        import java.util.List;

/**
 * Created by Kristen on 29/10/2015.
 */

@RestController
@RequestMapping(value="/ord/**")
public class OrdersPage
{
    @Autowired
    private OrdersService service;

    @RequestMapping(value="/orderProducts/{id}", method= RequestMethod.GET)
    public List<OrderProduct> getOrderProducts(@PathVariable Long id)
    {
        return service.getOrderProducts(id);
    }

    @RequestMapping(value = "/orders/", method = RequestMethod.GET)
    public ResponseEntity<List<Orders>> listAllOrders()
    {
        List<Orders> ordersList = service.findAll();
        if(ordersList.isEmpty())
        {
            return new ResponseEntity<List<Orders>>(HttpStatus.NOT_FOUND);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Orders>>(ordersList, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> getOrder(@PathVariable("id") long id)
    {
        System.out.println("Fetching Customer with id " + id);
        Orders order = service.findById(id);
        if (order == null)
        {
            System.out.println("Order with id " + id + " not found");
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Orders>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createOrder(@RequestBody Orders order, UriComponentsBuilder ucBuilder)
    {
        System.out.println("Creating Order " + order.getId());

        service.create(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/order/{id}").buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Orders> updateOrder(@PathVariable("id") long id, @RequestBody Orders order)
    {
        System.out.println("Updating Order " + id);

        Orders currentOrder = service.findById(id);

        if (currentOrder==null)
        {
            System.out.println("Order with id " + id + " not found");
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }

        Orders updatedOrder = new Orders
                .Builder(currentOrder.getOrderStatus())
                .copy(currentOrder)
                .dateOrderPlaced(order.getDateOrderPlaced())
                .dateOrderPaid(order.getDateOrderPaid())
                .totalOrderPrice(order.getTotalOrderPrice())
                .orderProductList(order.getOrderProductList())
                .build();
        service.edit(updatedOrder);
        return new ResponseEntity<Orders>(updatedOrder, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/delete/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Orders> deleteOrder(@PathVariable("id") long id, @RequestBody Orders ord)
    {
        System.out.println("Fetching & Deleting Order with id " + id);

        Orders order = service.findById(id);
        if (order == null)
        {
            System.out.println("Unable to delete. Order with id " + id + " not found");
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }

        service.delete(order);
        return new ResponseEntity<Orders>(HttpStatus.OK);
    }
}

