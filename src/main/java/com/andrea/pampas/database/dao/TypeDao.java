package com.andrea.pampas.database.dao;

import com.andrea.pampas.database.model.Type;
import com.andrea.pampas.exception.NotFoundException;

import java.util.List;

public interface TypeDao {

    /**
     * Returns a list of types
     * @return the list of types
     */
    List<Type> list();

    /**
     * Create a new type
     * @param type The type
     * @return the created type
     */
    Type create(String type) throws NotFoundException;

    /**
     * Gets a type
     * @param id The type id
     * @return the type
     */
    Type get(int id) throws NotFoundException;

    /**
     * Updates a type
     * @param id The type id
     * @return updated type
     */
    Type update(int id, String type) throws NotFoundException;

    /**
     *Deletes a type by id
     * @param id The type id
     */
    void delete(int id) throws NotFoundException;

}
