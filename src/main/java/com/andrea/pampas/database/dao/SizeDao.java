package com.andrea.pampas.database.dao;

import com.andrea.pampas.database.model.Color;
import com.andrea.pampas.database.model.Size;
import com.andrea.pampas.exception.NotFoundException;

import java.util.List;

public interface SizeDao {

    /**
     * Returns a list of sizes
     * @return the list of sizes
     */
    List<Size> list();

    /**
     * Create a new size
     * @param inches The size in inches
     * @return the created size in inches
     */
    Size create(double inches) throws NotFoundException;

    /**
     * Gets a size
     * @param id The size id
     * @return the size
     */
    Size get(int id) throws NotFoundException;

    /**
     * Updates a size
     * @param id The size id
     * @param inches to update
     * @return updated size
     */
    Size update(int id, double inches) throws NotFoundException;

    /**
     *Deletes a size by id
     * @param id The size id
     */
    void delete(int id) throws NotFoundException;

}
