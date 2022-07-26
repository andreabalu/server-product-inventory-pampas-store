package com.andrea.pampas.database.dao;

import com.andrea.pampas.database.model.Type;
import com.andrea.pampas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class JdbcTypeDao implements TypeDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Type> list() {
        String sql = "SELECT * FROM types";

        return jdbcTemplate.query(
                sql,
                (row, rowNum) ->
                        new Type (
                                row.getInt("id"),
                                row.getString("type")
                        )
        );
    }

    @Override
    public Type create(String type) throws NotFoundException {
        String sql = "INSERT INTO types(pampas_type) VALUES (?) RETURNING id";

        int newId = jdbcTemplate.queryForObject(sql, Integer.class, type);
        return this.get(newId);

    }

    @Override
    public Type get(int id) throws NotFoundException {
        String sql = "SELECT * FROM types WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                            new Type(
                                    rs.getInt("id"),
                                    rs.getString("type")
                            ),
                    new Object[]{id}
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Type not found");
        }
    }

    @Override
    public Type update(int id, String type) throws NotFoundException {
        this.get(id);
        String sql = "UPDATE types SET pampas_type = ? WHERE id = ? ";
        jdbcTemplate.update(sql, type, id);
        return new Type(id, type);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        this.get(id);
        String deleteQuery = "DELETE FROM types WHERE id = ? ";
        jdbcTemplate.update(deleteQuery, id);
    }
}
