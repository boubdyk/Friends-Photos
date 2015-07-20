package com.bionic.friendsphotos.dao;

import java.io.Serializable;

/**
 * Created by boubdyk on 15.07.2015.
 */
public interface GenericDao <T, PK extends Serializable> {

    /** Save newInstance object to DataBase
     */
    PK create(T newInstance);

    /**
     * Get object from DataBase using id like PK
     */
    T read(PK id);

    /**
     * Save changes to DataBase that has been made in object
     */
    T update(T transientObject);

    /**
     * Delete object from DataBase
     */
    void delete(PK persistentObject);
}
