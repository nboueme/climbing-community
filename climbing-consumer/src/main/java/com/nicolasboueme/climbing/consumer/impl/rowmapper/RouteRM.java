package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRM implements RowMapper<Route> {
    public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
        Route route = new Route();
        route.setName(rs.getString("name"));
        route.setHeight(rs.getInt("height"));
        route.setPointsNumber(rs.getInt("points_number"));
        route.setQuotation(rs.getString("quotation"));
        route.setPublicationId(rs.getInt("publication_id"));
        return route;
    }
}
