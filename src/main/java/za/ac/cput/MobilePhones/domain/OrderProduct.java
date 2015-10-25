package za.ac.cput.MobilePhones.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class OrderProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantity;

    private OrderProduct() {}

    public OrderProduct(Builder builder) {
        this.id = builder.id;
        this.quantity = builder.quantity;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public static class Builder {

        private Long id;
        private int quantity;

        public Builder(int quantity) {
            this.quantity = quantity;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder copy(OrderProduct orderProduct) {
            this.id = orderProduct.id;
            this.quantity = orderProduct.quantity;
            return this;
        }

        public OrderProduct build() {
            return new OrderProduct(this);
        }

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }

}
