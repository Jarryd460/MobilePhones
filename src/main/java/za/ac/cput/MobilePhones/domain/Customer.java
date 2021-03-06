package za.ac.cput.MobilePhones.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
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
    @Embedded
    private Demographic demographic;
    private String dateOfBirth;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private List<Orders> orderList;
    @Embedded
    private Login login;
    private String isAdmin;
    private String pictureExtension;
    @Lob
    @JsonIgnore
    private byte[] picture;

    private Customer() {}

    public Customer(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.demographic = builder.demographic;
        this.dateOfBirth = builder.dateOfBirth;
        this.contact = builder.contact;
        this.address = builder.address;
        this.orderList = builder.orderList;
        this.login = builder.login;
        this.isAdmin = builder.isAdmin;
        this.pictureExtension = builder.pictureExtension;
        this.picture = builder.picture;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Demographic getDemographic() {
        return demographic;
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

    public Login getLogin() {
        return login;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public String getPictureExtension() {
        return pictureExtension;
    }

    public byte[] getPicture() {
        return picture;
    }

    public static class Builder {

        private Long id;
        private Name name;
        private Demographic demographic;
        private String dateOfBirth;
        private Contact contact;
        private Address address;
        private List<Orders> orderList;
        private Login login;
        private String isAdmin;
        private String pictureExtension;
        private byte[] picture;

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

        public Builder demographic(Demographic demographic) {
            this.demographic = demographic;
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

        public Builder login(Login login) {
            this.login = login;
            return this;
        }

        public Builder isAdmin(String isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }

        public Builder pictureExtension(String pictureExtension) {
            this.pictureExtension = pictureExtension;
            return this;
        }

        public Builder picture(byte[] picture) {
            this.picture = picture;
            return this;
        }

        public Builder copy(Customer customer) {
            this.id = customer.id;
            this.name = customer.name;
            this.demographic = customer.demographic;
            this.dateOfBirth = customer.dateOfBirth;
            this.contact = customer.contact;
            this.address = customer.address;
            this.orderList = customer.orderList;
            this.login = customer.login;
            this.isAdmin = customer.isAdmin;
            this.pictureExtension = customer.pictureExtension;
            this.picture = customer.picture;
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
                ", demographic=" + demographic +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", contact=" + contact +
                ", address=" + address +
                ", orderList=" + orderList +
                ", login=" + login +
                ", isAdmin='" + isAdmin + '\'' +
                ", pictureExtension='" + pictureExtension + '\'' +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }

}
