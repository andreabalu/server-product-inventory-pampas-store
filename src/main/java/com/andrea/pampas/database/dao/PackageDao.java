package com.andrea.pampas.database.dao;

import com.andrea.pampas.database.model.Color;
import com.andrea.pampas.database.model.Package;
import com.andrea.pampas.exception.NotFoundException;

import java.util.List;

public interface PackageDao {

    /**
     * Returns a list of packages
     * @return the list of packages
     */
    List<Package> list();

    /**
     * Create a new package
     * @param name The package name
     * @param description The package description
     * @param stems The number of stems
     * @param colorId
     * @param sizeId
     * @param typeId
     * @param quantity
     * @param price
     * @return the created package
     */
    Package create(String name, String description, int stems, int colorId, int sizeId, int typeId, int quantity, double price) throws NotFoundException;

    /**
     * Gets a package
     * @param id The package id
     * @return the package
     */
    Package get(int id) throws NotFoundException;

    /**
     * Updates a package
     * @param id The package id
     * @param name The package name
     * @param description The package description
     * @param stems The number of stems
     * @param colorId
     * @param sizeId
     * @param typeId
     * @param quantity
     * @param price
     * @return updated package
     */
    Package update(int id, String name, String description, int stems, int colorId, int sizeId, int typeId, int quantity, double price) throws NotFoundException;

    /**
     *Deletes a package by id
     * @param id The package id
     */
    void delete(int id) throws NotFoundException;
}
