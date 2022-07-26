package com.andrea.pampas.database.dao;

import com.andrea.pampas.database.model.Color;
import com.andrea.pampas.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcColorDao implements ColorDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcColorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Color> list() {
        String sql = "SELECT * FROM colors";

        return jdbcTemplate.query(
                sql,
                (row, rowNum) ->
                        new Color (
                                row.getInt("id"),
                                row.getString("color")
                        )
        );
    }

    @Override
    public Color create(String color) {
        String sql = "INSERT INTO colors(color) VALUES (?) RETURNING id";

        int newId = jdbcTemplate.queryForObject(sql, Integer.class, color);
        return new Color(newId, color);

    }

    @Override
    public Color get(int id) throws NotFoundException {
        String sql = "SELECT * FROM colors WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Color(
                        rs.getInt("id"),
                        rs.getString("color")
                ),
                new Object[]{id}
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Color not found");
        }
    }

    @Override
    public Color update(int id, String color) throws NotFoundException {
        this.get(id);
        String sql = "UPDATE colors SET color = ? WHERE id = ? ";
        jdbcTemplate.update(sql, color, id);
        return new Color(id, color);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        this.get(id);
        String deleteQuery = "DELETE FROM colors WHERE id = ? ";
        jdbcTemplate.update(deleteQuery, id);
    }
}
