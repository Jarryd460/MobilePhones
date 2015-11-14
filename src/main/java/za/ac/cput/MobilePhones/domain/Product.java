package za.ac.cput.MobilePhones.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by student on 2015/10/14.
 */

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String manufacturer;
    private BigDecimal price;
    private String operatingSystem;
    private String screenSize;
    private String touchScreen;
    private String camera;
    private String memory;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private List<OrderProduct> orderProductList;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private List<ProductPrice> productPriceList;
    @Lob
    private byte[] picture;

    private Product() {}

    public Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.manufacturer = builder.manufacturer;
        this.price = builder.price;
        this.operatingSystem = builder.operatingSystem;
        this.screenSize = builder.screenSize;
        this.touchScreen = builder.touchScreen;
        this.camera = builder.camera;
        this.memory = builder.memory;
        this.orderProductList = builder.orderProductList;
        this.productPriceList = builder.productPriceList;
        this.picture = builder.picture;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getTouchScreen() {
        return touchScreen;
    }

    public String getCamera() {
        return camera;
    }

    public String getMemory() {
        return memory;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public List<ProductPrice> getProductPriceList() {
        return productPriceList;
    }

    public byte[] getPicture() {
        return picture;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String manufacturer;
        private BigDecimal price;
        private String operatingSystem;
        private String screenSize;
        private String touchScreen;
        private String camera;
        private String memory;
        private List<OrderProduct> orderProductList;
        private List<ProductPrice> productPriceList;
        private byte[] picture;

        public Builder(String name) {
            this.name = name;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder manufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Builder screenSize(String screenSize) {
            this.screenSize = screenSize;
            return this;
        }

        public Builder touchScreen(String touchScreen) {
            this.touchScreen = touchScreen;
            return this;
        }

        public Builder camera(String camera) {
            this.camera = camera;
            return this;
        }

        public Builder memory(String memory) {
            this.memory = memory;
            return this;
        }

        public Builder orderProductList(List<OrderProduct> orderProductList) {
            this.orderProductList = orderProductList;
            return this;
        }

        public Builder productPriceList(List<ProductPrice> productPriceList) {
            this.productPriceList = productPriceList;
            return this;
        }

        public Builder picture(byte[] picture) {
            this.picture = picture;
            return this;
        }

        public Builder copy(Product product) {
            this.id = product.id;
            this.name = product.name;
            this.manufacturer = product.manufacturer;
            this.price = product.price;
            this.operatingSystem = product.operatingSystem;
            this.screenSize = product.screenSize;
            this.touchScreen = product.touchScreen;
            this.camera = product.camera;
            this.memory = product.memory;
            this.orderProductList = product.orderProductList;
            this.productPriceList = product.productPriceList;
            this.picture = product.picture;
            return this;
        }

        public Product build() {
            return new Product(this);
        }

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", screenSize='" + screenSize + '\'' +
                ", touchScreen='" + touchScreen + '\'' +
                ", camera='" + camera + '\'' +
                ", memory='" + memory + '\'' +
                ", orderProductList=" + orderProductList +
                ", productPriceList=" + productPriceList +
                ", picture=" + Arrays.toString(picture) +
                '}';
    }

}
