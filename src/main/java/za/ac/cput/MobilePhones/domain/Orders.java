package za.ac.cput.MobilePhones.domain;

        import javax.persistence.*;
        import java.io.Serializable;
        import java.math.BigDecimal;
        import java.util.List;

/**
 * Created by Kristen on 25/10/2015.
 */

@Entity
public class Orders implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderStatus;
    private String dateOrderPlaced;
    private String dateOrderPaid;
    private BigDecimal totalOrderPrice;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private List<OrderProduct> orderProductList;

    private Orders(){}

    public Orders(Builder builder)
    {
        this.id = builder.id;
        this.orderStatus = builder.orderStatus;
        this.dateOrderPlaced = builder.dateOrderPlaced;
        this.dateOrderPaid = builder.dateOrderPaid;
        this.totalOrderPrice = builder.totalOrderPrice;
        this.orderProductList = builder.orderProductList;
    }

    public Long getId()
    {
        return id;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    public String getDateOrderPlaced()
    {
        return dateOrderPlaced;
    }

    public String getDateOrderPaid()
    {
        return dateOrderPaid;
    }

    public BigDecimal getTotalOrderPrice()
    {
        return totalOrderPrice;
    }

    public List<OrderProduct> getOrderProductList()
    {
        return orderProductList;
    }

    public static class Builder
    {
        private Long id;
        private String orderStatus;
        private String dateOrderPlaced;
        private String dateOrderPaid;
        private BigDecimal totalOrderPrice;
        private List<OrderProduct> orderProductList;

        public Builder(String orderStatus)
        {
            this.orderStatus = orderStatus;
        }

        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder orderStatus(String orderStatus)
        {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder dateOrderPlaced(String dateOrderPlaced)
        {
            this.dateOrderPlaced = dateOrderPlaced;
            return this;
        }

        public Builder dateOrderPaid(String dateOrderPaid)
        {
            this.dateOrderPaid = dateOrderPaid;
            return this;
        }

        public Builder totalOrderPrice(BigDecimal totalOrderPrice)
        {
            this.totalOrderPrice = totalOrderPrice;
            return this;
        }

        public Builder orderProductList(List<OrderProduct> orderProductList)
        {
            this.orderProductList = orderProductList;
            return this;
        }

        public Builder copy(Orders order)
        {
            this.id = order.id;
            this.orderStatus = order.orderStatus;
            this.dateOrderPlaced = order.dateOrderPlaced;
            this.dateOrderPaid = order.dateOrderPaid;
            this.totalOrderPrice = order.totalOrderPrice;
            this.orderProductList = order.orderProductList;
            return this;
        }

        public Orders build()
        {
            return new Orders(this);
        }
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "Orders{" +
                "id=" + id +
                ", orderStatus='" + orderStatus + '\'' +
                ", dateOrderPlaced='" + dateOrderPlaced + '\'' +
                ", dateOrderPaid='" + dateOrderPaid + '\'' +
                ", totalOrderPrice=" + totalOrderPrice +
                ", orderProductList=" + orderProductList +
                '}';
    }
}

