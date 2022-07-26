package com.andrea.pampas.controller;

import com.andrea.pampas.database.dao.TypeDao;
import com.andrea.pampas.database.model.Type;
import com.andrea.pampas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController
{
    private TypeDao typeDao;

    @Autowired
    public TypeController(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @GetMapping
    public List<Type> list() {
        return typeDao.list();
    }

    @PostMapping
    public Type create(@Valid @RequestBody Type type) throws NotFoundException {
        return typeDao.create(type.getPampasType());
    }

    @GetMapping("/{id}")
    public Type get(@PathVariable int id) throws NotFoundException {
        return typeDao.get(id);
    }

    @PutMapping("/{id}")
    public Type update(@PathVariable int id, @Valid @RequestBody Type type) throws NotFoundException {
        return typeDao.update(id, type.getPampasType());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        typeDao.delete(id);
    }
}
