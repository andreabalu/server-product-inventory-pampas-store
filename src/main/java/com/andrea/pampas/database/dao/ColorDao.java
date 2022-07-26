package com.andrea.pampas.database.dao;

import com.andrea.pampas.database.model.Color;
import com.andrea.pampas.exception.NotFoundException;

import java.util.List;

public interface ColorDao {

    /**
     * Returns a list of colors
     * @return the list of colors
     */
    List<Color> list();

    /**
     * Create a new color
     * @param color The color name
     * @return the created color
     */
    Color create(String color);

    /**
     * Gets a color
     * @param id The color id
     * @return the color
     */
    Color get(int id) throws NotFoundException;

    /**
     * Updates a color
     * @param id The color id
     * @param color updated color
     * @return updated color
     */
    Color update(int id, String color) throws NotFoundException;

    /**
     *Deletes a color by id
     * @param id The color id
     */
    void delete(int id) throws NotFoundException;

}
