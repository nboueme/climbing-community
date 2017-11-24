package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.RouteDao;
import com.nicolasboueme.climbing.model.entity.Route;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RouteDaoImpl extends AbstractDaoImpl implements RouteDao {

    public List<Route> listRoutesFromParent(int sectorId) {
        String sql = "SELECT publication.name, route.height, route.points_number, route.quotation, route.publication_id FROM publication, route WHERE publication.id = route.publication_id AND route.sector_id = ? AND type_route = ?;";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        RowMapper<Route> rowMapper = new RowMapper<Route>() {
            public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
                Route route = new Route();
                route.setName(rs.getString("name"));
                route.setHeight(rs.getInt("height"));
                route.setPointsNumber(rs.getInt("points_number"));
                route.setQuotation(rs.getString("quotation"));
                route.setPublicationId(rs.getInt("publication_id"));
                return route;
            }
        };

        return jdbcTemplate.query(sql, rowMapper, sectorId, "route");
    }

    public Route getRoute(int routeId) {
        String sql = "SELECT publication.name, route.height, route.points_number, route.quotation FROM publication, route WHERE publication.id = route.publication_id AND route.publication_id = ?;";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        RowMapper<Route> rowMapper = new RowMapper<Route>() {
            public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
                Route route = new Route();
                route.setName(rs.getString("name"));
                route.setHeight(rs.getInt("height"));
                route.setPointsNumber(rs.getInt("points_number"));
                route.setQuotation(rs.getString("quotation"));
                return route;
            }
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, routeId);
    }
}
