package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.Sector;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectorRM implements RowMapper<Sector> {
    public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sector sector = new Sector();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (rs.getMetaData().getColumnName(i).equals("name")) sector.setName(rs.getString("name"));
            else if (rs.getMetaData().getColumnName(i).equals("publication_id")) sector.setPublicationId(rs.getInt("publication_id"));
            else if (rs.getMetaData().getColumnName(i).equals("spot_id")) sector.setSpotId(rs.getInt("spot_id"));
            else if (rs.getMetaData().getColumnName(i).equals("height")) sector.setHeight(rs.getInt("height"));
        }

        return sector;
    }
}
