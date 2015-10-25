package za.ac.cput.MobilePhones.domain;

        import org.junit.Assert;
        import org.junit.Test;
        import za.ac.cput.MobilePhones.conf.factory.NameFactory;

/**
 * Created by Kristen on 25/10/2015.
 */
public class NameTest
{
    @Test
    public void testCreate()
    {
        Name name = NameFactory.createName("John", "", "Doe");
        Assert.assertEquals(name.getFirstName(), "John");
        Assert.assertEquals(name.getLastName(), "Doe");
    }

    @Test
    public void testUpdate()
    {
        Name name = NameFactory.createName("John", "", "Doe");
        Name nameCopy = new Name.Builder(name.getLastName()).copy(name).lastName("Arnold").build();
        Assert.assertEquals(nameCopy.getFirstName(), "John");
        Assert.assertEquals(nameCopy.getLastName(), "Arnold");
    }
}

