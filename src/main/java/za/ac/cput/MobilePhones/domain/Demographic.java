package za.ac.cput.MobilePhones.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by student on 2016/01/20.
 */

@Embeddable
public class Demographic implements Serializable {

    private Sex gender;
    private String dateOfBirth;

    private Demographic() {}

    public Demographic(Builder builder) {
        this.gender = builder.gender;
        this.dateOfBirth = builder.dateOfBirth;
    }

    public Sex getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public static class Builder {

        private Sex gender;
        private String dateOfBirth;

        public Builder(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Builder gender(Sex gender) {
            this.gender = gender;
            return this;
        }

        public Builder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder copy(Demographic demographic) {
            this.gender = demographic.gender;
            this.dateOfBirth = demographic.dateOfBirth;
            return this;
        }

        public Demographic build() {
            return new Demographic(this);
        }

    }

    @Override
    public String toString() {
        return "Demographic{" +
                "gender=" + gender +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }

}
