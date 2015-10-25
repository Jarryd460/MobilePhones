package za.ac.cput.MobilePhones.conf.factory;

        import za.ac.cput.MobilePhones.domain.Name;

/**
 * Created by Kristen on 25/10/2015.
 */
public class NameFactory
{
    public static Name createName(String firstName, String middleName, String lastName)
    {
        return new Name.Builder(lastName)
                .firstName(firstName)
                .middleName(middleName)
                .build();
    }
}

