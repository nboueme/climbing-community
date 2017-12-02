package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.Sector;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectorRM implements RowMapper<Sector> {
    public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sector sector = new Sector();
        sector.setName(rs.getString("name"));
        sector.setPublicationId(rs.getInt("publication_id"));
        sector.setSpotId(rs.getInt("spot_id"));
        sector.setHeight(rs.getInt("height"));
        return sector;
    }
}
