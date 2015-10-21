package za.ac.cput.MobilePhones.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by student on 2015/10/14.
 */

@Embeddable
public class Contact implements Serializable {

    private String homePhoneNumber;
    private String mobilePhoneNumber;

    private Contact() {}

    public Contact(Builder builder) {
        this.homePhoneNumber = builder.homePhoneNumber;
        this.mobilePhoneNumber = builder.mobilePhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public static class Builder {

        private String homePhoneNumber;
        private String mobilePhoneNumber;

        public Builder(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
        }

        public Builder homePhoneNumber(String homePhoneNumber) {
            this.homePhoneNumber = homePhoneNumber;
            return this;
        }

        public Builder mobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
            return this;
        }

        public Builder copy(Contact contact) {
            this.homePhoneNumber = contact.homePhoneNumber;
            this.mobilePhoneNumber = contact.mobilePhoneNumber;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }

    }

    @Override
    public String toString() {
        return "Contact{" +
                "homePhoneNumber='" + homePhoneNumber + '\'' +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                '}';
    }

}
