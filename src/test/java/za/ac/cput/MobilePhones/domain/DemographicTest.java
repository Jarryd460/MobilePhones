package za.ac.cput.MobilePhones.domain;

import org.junit.Assert;
import org.junit.Test;
import za.ac.cput.MobilePhones.conf.factory.DemographicFactory;

/**
 * Created by student on 2016/01/21.
 */
public class DemographicTest {

    @Test
    public void testCreate() {
        Demographic demographic = DemographicFactory.createDemographic(Sex.Male,  "04-29-1992");
        Assert.assertEquals(demographic.getGender(), Sex.Male);
        Assert.assertEquals(demographic.getDateOfBirth(), "04-29-1992");
    }

    @Test
    public void testUpdate() {
        Demographic demographic = DemographicFactory.createDemographic(Sex.Male, "04-29-1992");
        Demographic demographicCopy = new Demographic.Builder(demographic.getDateOfBirth()).copy(demographic).build();
        Assert.assertEquals(demographicCopy.getGender(), Sex.Male);
        Assert.assertEquals(demographicCopy.getDateOfBirth(), "04-29-1992");
    }

}
