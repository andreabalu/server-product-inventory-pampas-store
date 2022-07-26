package com.andrea.pampas.controller;

import com.andrea.pampas.database.dao.SizeDao;
import com.andrea.pampas.database.model.Size;
import com.andrea.pampas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sizes")
public class SizeController
{
    private SizeDao sizeDao;

    @Autowired
    public SizeController(SizeDao sizeDao) {
        this.sizeDao = sizeDao;
    }

    @GetMapping
    public List<Size> list() {
        return sizeDao.list();
    }

    @PostMapping
    public Size create(@Valid @RequestBody Size size) throws NotFoundException {
        return sizeDao.create(size.getInches());
    }

    @GetMapping("/{id}")
    public Size get(@PathVariable int id) throws NotFoundException {
        return sizeDao.get(id);
    }

    @PutMapping("/{id}")
    public Size update(@PathVariable int id, @Valid @RequestBody Size size) throws NotFoundException {
        return sizeDao.update(id, size.getInches());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        sizeDao.delete(id);
    }
}
