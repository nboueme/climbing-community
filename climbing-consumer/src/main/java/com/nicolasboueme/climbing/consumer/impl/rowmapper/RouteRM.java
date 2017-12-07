package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRM implements RowMapper<Route> {
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        Route route = new Route();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (rs.getMetaData().getColumnName(i).equals("name")) route.setName(rs.getString("name"));
            else if (rs.getMetaData().getColumnName(i).equals("publication_id")) route.setPublicationId(rs.getInt("publication_id"));
            else if (rs.getMetaData().getColumnName(i).equals("sector_id")) route.setSectorId(rs.getInt("sector_id"));
            else if (rs.getMetaData().getColumnName(i).equals("parent_publication_id")) route.setParentPublicationId(rs.getInt("parent_publication_id"));
            else if (rs.getMetaData().getColumnName(i).equals("height")) route.setHeight(rs.getInt("height"));
            else if (rs.getMetaData().getColumnName(i).equals("quotation")) route.setQuotation(rs.getString("quotation"));
            else if (rs.getMetaData().getColumnName(i).equals("latitude")) route.setLatitude(rs.getDouble("latitude"));
            else if (rs.getMetaData().getColumnName(i).equals("longitude")) route.setLongitude(rs.getDouble("longitude"));
            else if (rs.getMetaData().getColumnName(i).equals("points_number")) route.setPointsNumber(rs.getInt("points_number"));
        }

        return route;
    }
}
