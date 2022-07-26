package com.andrea.pampas.controller;

import com.andrea.pampas.database.dao.PackageDao;
import com.andrea.pampas.database.model.Package;
import com.andrea.pampas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackageController {

    private PackageDao packageDao;

    @Autowired
    public PackageController(PackageDao packageDao) {
        this.packageDao = packageDao;
    }

    @GetMapping
    public List<Package> list() {
        return packageDao.list();
    }

    @PostMapping
    public Package create(@Valid @RequestBody Package packageItem) throws NotFoundException {
        return packageDao.create(
                packageItem.getName(),
                packageItem.getDescription(),
                packageItem.getStems(),
                packageItem.getColorId(),
                packageItem.getSizeId(),
                packageItem.getTypeId(),
                packageItem.getQuantity(),
                packageItem.getPrice());
    }

    @GetMapping("/{id}")
    public Package get(@PathVariable int id) throws NotFoundException {
        return packageDao.get(id);
    }

    @PutMapping("/{id}")
    public Package update(@PathVariable int id, @Valid @RequestBody Package packageItem) throws NotFoundException {
        return packageDao.update(
                id,
                packageItem.getName(),
                packageItem.getDescription(),
                packageItem.getStems(),
                packageItem.getColorId(),
                packageItem.getSizeId(),
                packageItem.getTypeId(),
                packageItem.getQuantity(),
                packageItem.getPrice());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        packageDao.delete(id);
    }
}
