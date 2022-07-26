package com.andrea.pampas.database.dao;

import com.andrea.pampas.database.model.Size;
import com.andrea.pampas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class JdbcSizeDao implements SizeDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcSizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Size> list() {
        String sql = "SELECT * FROM sizes";

        return jdbcTemplate.query(
                sql,
                (row, rowNum) ->
                        new Size(
                                row.getInt("id"),
                                row.getDouble("inches")
                        )
        );
    }

    @Override
    public Size create(double inches) throws NotFoundException {
        String sql = "INSERT INTO sizes(inches) VALUES (?) RETURNING id";

        int newId = jdbcTemplate.queryForObject(sql, Integer.class, inches);
        return this.get(newId);

    }

    @Override
    public Size get(int id) throws NotFoundException {
        String sql = "SELECT * FROM sizes WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                            new Size(
                                    rs.getInt("id"),
                                    rs.getInt("inches")
                            ),
                    new Object[]{id}
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Size not found");
        }
    }

    @Override
    public Size update(int id, double inches) throws NotFoundException {
        this.get(id);
        String sql = "UPDATE sizes SET inches = ? WHERE id = ? ";
        jdbcTemplate.update(sql, inches, id);
        return this.get(id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        this.get(id);
        String deleteQuery = "DELETE FROM sizes WHERE id = ? ";
        jdbcTemplate.update(deleteQuery, id);
    }
}
