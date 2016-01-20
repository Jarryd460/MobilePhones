package za.ac.cput.MobilePhones.conf.factory;

import za.ac.cput.MobilePhones.domain.Demographic;
import za.ac.cput.MobilePhones.domain.Sex;

/**
 * Created by student on 2016/01/21.
 */
public class DemographicFactory {

    public static Demographic createDemographic(Sex gender, String dateOfBirth) {
        return new Demographic.Builder(dateOfBirth).gender(gender).build();
    }

}
