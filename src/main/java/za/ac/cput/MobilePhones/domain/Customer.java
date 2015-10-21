package za.ac.cput.MobilePhones.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/10/14.
 */

@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Name name;
    private String sex;
    private String dateOfBirth;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private List<Orders> orderList;

    private Customer() {}

    public Customer(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.sex = builder.sex;
        this.dateOfBirth = builder.dateOfBirth;
        this.contact = builder.contact;
        this.address = builder.address;
        this.orderList = builder.orderList;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public List<Orders> getOrderList() {
        return orderList;
    }

    public static class Builder {

        private Long id;
        private Name name;
        private String sex;
        private String dateOfBirth;
        private Contact contact;
        private Address address;
        private List<Orders> orderList;

        public Builder(Name name) {
            this.name = name;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder contact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }

        public Builder orderList(List<Orders> orderList) {
            this.orderList = orderList;
            return this;
        }

        public Builder copy(Customer customer) {
            this.id = customer.id;
            this.name = customer.name;
            this.sex = customer.sex;
            this.dateOfBirth = customer.dateOfBirth;
            this.contact = customer.contact;
            this.address = customer.address;
            this.orderList = customer.orderList;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name=" + name +
                ", sex='" + sex + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", contact=" + contact +
                ", address=" + address +
                ", orderList=" + orderList +
                '}';
    }

}
