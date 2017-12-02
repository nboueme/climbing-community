package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.RouteDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.RouteRM;
import com.nicolasboueme.climbing.model.entity.Route;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Types;
import java.util.List;

public class RouteDaoImpl extends AbstractDaoImpl implements RouteDao {

    public List<Route> listRoutesFromParent(int sectorId) {
        String sql = "SELECT publication.name, route.publication_id, route.height, quotation, points_number FROM publication, route WHERE publication.id = route.publication_id AND route.sector_id = :sector_id AND type_route = :type_route;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("sector_id", sectorId, Types.INTEGER);
        args.addValue("type_route", "route", Types.VARCHAR);

        RowMapper<Route> rowMapper = new RouteRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public Route getRoute(int routeId) {
        String sql = "SELECT publication.name, route.publication_id, route.height, quotation, points_number FROM publication, route WHERE publication.id = route.publication_id AND route.publication_id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", routeId, Types.INTEGER);

        RowMapper<Route> rowMapper = new RouteRM();

        return getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
    }

    public List<Route> listLengthsFromRoute(int routeId) {
        String sql = "SELECT publication.name, route.height, route.points_number, route.quotation, route.publication_id FROM publication, route WHERE publication.id = route.publication_id AND route.parent_publication_id = :route_id AND type_route = :type_route;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("route_id", routeId, Types.INTEGER);
        args.addValue("type_route", "length", Types.VARCHAR);

        RowMapper<Route> rowMapper = new RouteRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

}
