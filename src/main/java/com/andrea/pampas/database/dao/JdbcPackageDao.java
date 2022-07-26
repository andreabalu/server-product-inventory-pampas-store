package com.andrea.pampas.database.dao;

import com.andrea.pampas.database.model.Package;
import com.andrea.pampas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcPackageDao implements PackageDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPackageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Package> list() {
        String sql = "SELECT * FROM packages";

        return jdbcTemplate.query(
                sql,
                (row, rowNum) ->
                        new Package(
                                row.getInt("id"),
                                row.getString("name"),
                                row.getString("description"),
                                row.getInt("stems"),
                                row.getInt("colorId"),
                                row.getInt("sizeId"),
                                row.getInt("typeId"),
                                row.getInt("quantity"),
                                row.getDouble("price")
                        )
        );
    }

    @Override
    public Package create(String name, String description, int stems, int colorId, int sizeId, int typeId, int quantity, double price) throws NotFoundException {
        String sql = "INSERT INTO packages(name, description, stems, color_id, size_id, type_id, quantity, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        int newId = jdbcTemplate.queryForObject(sql, Integer.class, name, description, stems, colorId, sizeId, typeId, quantity, price);
        return get(newId);
    }

    @Override
    public Package get(int id) throws NotFoundException {
        String sql = "SELECT * FROM packages WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (row, rowNum) ->
                            new Package(
                                    row.getInt("id"),
                                    row.getString("name"),
                                    row.getString("description"),
                                    row.getInt("stems"),
                                    row.getInt("colorId"),
                                    row.getInt("sizeId"),
                                    row.getInt("typeId"),
                                    row.getInt("quantity"),
                                    row.getDouble("price")
                            ),
                    new Object[]{id}
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Package not found");
        }
    }

    @Override
    public Package update(int id, String name, String description, int stems, int colorId, int sizeId, int typeId, int quantity, double price) throws NotFoundException {
        this.get(id);
        String sql = "UPDATE packages SET name = ?, description = ?, stems = ?, color_id = ?, size_id = ?, type_id = ?, quantity = ?, price = ? WHERE id = ? ";
        jdbcTemplate.update(sql, name, description, stems, colorId, sizeId, typeId, quantity, price, id);
        return this.get(id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        this.get(id);
        String deleteQuery = "DELETE FROM packages WHERE id = ? ";
        jdbcTemplate.update(deleteQuery, id);
    }
}
