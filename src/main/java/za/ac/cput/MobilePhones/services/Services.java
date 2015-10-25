package za.ac.cput.MobilePhones.services;

import java.util.List;

public interface Services<S, ID> {

    S findById(ID id);

    S create(S entity);

    S edit(S entity);

    void delete(S entity);

    List<S> findAll();

}
