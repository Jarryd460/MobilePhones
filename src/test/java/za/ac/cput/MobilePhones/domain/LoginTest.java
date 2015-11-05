package za.ac.cput.MobilePhones.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.MobilePhones.conf.factory.LoginFactory;

/**
 * Created by student on 2015/08/10.
 */
public class LoginTest {

    @Test
    public void testCreate() {
        Login login = LoginFactory.createLogin("Jarryd", "Deane");
        Assert.assertEquals(login.getUserName(), "Jarryd");
        Assert.assertEquals(login.getPassword(), "Deane");
    }

    @Test
    public void testUpdate() {
        Login login = LoginFactory.createLogin("Jarryd", "Deane");
        Login logincopy = new Login.Builder(login.getUserName(), login.getPassword()).copy(login).password("12345").build();
        Assert.assertEquals(logincopy.getUserName(), "Jarryd");
        Assert.assertEquals(logincopy.getPassword(), "12345");
    }

}
