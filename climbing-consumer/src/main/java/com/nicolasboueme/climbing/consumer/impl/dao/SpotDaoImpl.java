package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.SpotDao;
import com.nicolasboueme.climbing.model.entity.Spot;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpotDaoImpl extends AbstractDaoImpl implements SpotDao {

    public List<Spot> listSpots() {
        String sql = "SELECT publication.name, spot.height, spot.publication_id FROM publication, spot WHERE publication.id = spot.publication_id;";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        RowMapper<Spot> rowMapper = new RowMapper<Spot>() {
            public Spot mapRow(ResultSet rs, int rowNum) throws SQLException {
                Spot spot = new Spot();
                spot.setName(rs.getString("name"));
                spot.setHeight(rs.getInt("height"));
                spot.setPublicationId(rs.getInt("publication_id"));
                return spot;
            }
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}
