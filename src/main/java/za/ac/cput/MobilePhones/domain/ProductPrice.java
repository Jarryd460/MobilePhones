package za.ac.cput.MobilePhones.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class ProductPrice implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dateFrom;
    private BigDecimal productPrice;

    private ProductPrice() {}

    public ProductPrice(Builder builder) {
        this.id = builder.id;
        this.dateFrom = builder.dateFrom;
        this.productPrice = builder.productPrice;
    }

    public Long getId() {
        return id;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public static class Builder {

        private Long id;
        private String dateFrom;
        private BigDecimal productPrice;

        public Builder(BigDecimal productPrice) {
            this.productPrice = productPrice;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder dateFrom(String dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Builder productPrice(BigDecimal productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public Builder copy(ProductPrice productPrice) {
            this.id = productPrice.id;
            this.dateFrom = productPrice.dateFrom;
            this.productPrice = productPrice.productPrice;
            return this;
        }

        public ProductPrice build() {
            return new ProductPrice(this);
        }

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "id=" + id +
                ", dateFrom='" + dateFrom + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }

}
