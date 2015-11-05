package za.ac.cput.MobilePhones.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by student on 2015/08/10.
 */
@Embeddable
public class Login implements Serializable {

    private String userName;
    private String password;

    private Login() {}

    public Login(Builder builder) {
        this.userName = builder.userName;
        this.password = builder.password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {

        private String userName;
        private String password;

        public Builder(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder copy(Login login) {
            this.userName = login.userName;
            this.password = login.password;
            return this;
        }

        public Login build() {
            return new Login(this);
        }
    }

    @Override
    public String toString() {
        return "Login{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
