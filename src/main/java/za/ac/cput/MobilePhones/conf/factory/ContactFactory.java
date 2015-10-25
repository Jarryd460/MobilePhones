package za.ac.cput.MobilePhones.conf.factory;

import za.ac.cput.MobilePhones.domain.Contact;

/**
 * Created by student on 2015/10/18.
 */
public class ContactFactory {

    public static Contact createContact(String homePhoneNumber, String mobilePhoneNumber) {
        return new Contact.Builder(mobilePhoneNumber).homePhoneNumber(homePhoneNumber).build();
    }

}
