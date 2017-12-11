package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.Spot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpotRM implements RowMapper<Spot> {
    public Spot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Spot spot = new Spot();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (rs.getMetaData().getColumnName(i).equals("spot_id")) spot.setPublicationId(rs.getInt("spot_id"));
            else if (rs.getMetaData().getColumnName(i).equals("name")) spot.setName(rs.getString("name"));
            else if (rs.getMetaData().getColumnName(i).equals("description")) spot.setDescription(rs.getString("description"));
            else if (rs.getMetaData().getColumnName(i).equals("height")) spot.setHeight(rs.getInt("height"));
        }

        return spot;
    }
}
