package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.RouteDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.RouteRM;
import com.nicolasboueme.climbing.model.entity.Route;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Types;
import java.util.List;

public class RouteDaoImpl extends AbstractDaoImpl implements RouteDao {

    public List<Route> listRoutesFromParent(Route route) {
        String sql = "SELECT publication.name, route.publication_id, route.height, quotation, points_number FROM publication, route WHERE publication.id = route.publication_id AND route.sector_id = :sector_id AND type_route = :type_route;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("sector_id", route.getSectorId(), Types.INTEGER);
        args.addValue("type_route", "route", Types.VARCHAR);

        RowMapper<Route> rowMapper = new RouteRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public void addRoute(Route route) {
        String sql;

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", route.getUserAccountId(), Types.INTEGER);
        args.addValue("publication_name", route.getName(), Types.VARCHAR);
        args.addValue("sector_id", route.getSectorId(), Types.INTEGER);
        args.addValue("route_height", route.getHeight(), Types.INTEGER);
        args.addValue("route_quotation", route.getQuotation(), Types.VARCHAR);
        args.addValue("route_latitude", route.getLatitude(), Types.NUMERIC);
        args.addValue("route_longitude", route.getLongitude(), Types.NUMERIC);
        args.addValue("route_points", route.getPointsNumber(), Types.INTEGER);
        args.addValue("type_route", route.getTypeRoute(), Types.VARCHAR);

        if (route.getParentPublicationId() != 0) {
            sql = "INSERT INTO publication (user_account_id, name) " +
                    "VALUES (:user_id, :publication_name);" +
                    "INSERT INTO route (publication_id, sector_id, parent_publication_id, height, quotation, latitude, longitude, points_number, type_route) " +
                    "VALUES ((SELECT LASTVAL()), :sector_id, :parent_id, :route_height, :route_quotation, :route_latitude, :route_longitude, :route_points, :type_route);";
            args.addValue("parent_id", route.getParentPublicationId(), Types.INTEGER);
        } else {
            sql = "INSERT INTO publication (user_account_id, name) " +
                    "VALUES (:user_id, :publication_name);" +
                    "INSERT INTO route (publication_id, sector_id, height, quotation, latitude, longitude, points_number, type_route) " +
                    "VALUES ((SELECT LASTVAL()), :sector_id, :route_height, :route_quotation, :route_latitude, :route_longitude, :route_points, :type_route);";
        }

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public Route getRoute(Route route) {
        String sql = "SELECT publication.name, route.publication_id, route.height, quotation, points_number FROM publication, route WHERE publication.id = route.publication_id AND route.publication_id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", route.getPublicationId(), Types.INTEGER);

        RowMapper<Route> rowMapper = new RouteRM();

        return getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
    }

    public void updateRoute(Route route) {
        String sql = "UPDATE route SET height = :route_height, quotation = :route_quotation, latitude = :route_latitude, longitude = :route_longitude, points_number = :route_points " +
                "WHERE route.publication_id = :publication_id;" +
                "UPDATE publication SET name = :route_name, updated_at = now() WHERE publication.id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", route.getPublicationId(), Types.INTEGER);
        args.addValue("route_name", route.getName(), Types.VARCHAR);
        args.addValue("route_height", route.getHeight(), Types.INTEGER);
        args.addValue("route_quotation", route.getQuotation(), Types.VARCHAR);
        args.addValue("route_latitude", route.getLatitude(), Types.NUMERIC);
        args.addValue("route_longitude", route.getLongitude(), Types.NUMERIC);
        args.addValue("route_points", route.getPointsNumber(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteRoute(Route route) {
        String sql = "DELETE FROM route WHERE route.publication_id = :publication_id;" +
                "DELETE FROM publication WHERE publication.id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", route.getPublicationId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public List<Route> listLengthsFromRoute(Route route) {
        String sql = "SELECT publication.name, route.height, route.points_number, route.quotation, route.publication_id FROM publication, route WHERE publication.id = route.publication_id AND route.parent_publication_id = :route_id AND type_route = :type_route;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("route_id", route.getParentPublicationId(), Types.INTEGER);
        args.addValue("type_route", "length", Types.VARCHAR);

        RowMapper<Route> rowMapper = new RouteRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }
}
