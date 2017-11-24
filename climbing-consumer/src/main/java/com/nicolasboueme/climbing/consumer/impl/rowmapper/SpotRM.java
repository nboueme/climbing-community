package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.Spot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpotRM implements RowMapper<Spot> {
    public Spot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Spot spot = new Spot();
        spot.setName(rs.getString("name"));
        spot.setHeight(rs.getInt("height"));
        spot.setPublicationId(rs.getInt("publication_id"));
        return spot;
    }
}
