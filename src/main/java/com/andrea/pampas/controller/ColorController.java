package com.andrea.pampas.controller;

import com.andrea.pampas.database.dao.ColorDao;
import com.andrea.pampas.database.model.Color;
import com.andrea.pampas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/colors")
public class ColorController {
    private ColorDao colorDao;

    @Autowired
    public ColorController(ColorDao colorDao) {
        this.colorDao = colorDao;
    }

    @GetMapping
    public List<Color> getAllColors() {
        return colorDao.list();
    }

    @PostMapping
    public Color createColor(@Valid @RequestBody Color color) {
        return colorDao.create(color.getColor());
    }

    @GetMapping("/{id}")
    public Color getColorById(@PathVariable int id) throws NotFoundException {
        return colorDao.get(id);
    }

    @PutMapping("/{id}")
    public Color updateColor(@PathVariable int id, @Valid @RequestBody Color color) throws NotFoundException {
        return colorDao.update(id, color.getColor());
    }

    @DeleteMapping("/{id}")
    public void deleteColor(@PathVariable int id) throws NotFoundException {
        colorDao.delete(id);
    }
}
