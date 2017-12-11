package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.TopoDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.SpotRM;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.TopoRM;
import com.nicolasboueme.climbing.model.entity.Spot;
import com.nicolasboueme.climbing.model.entity.Topo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Types;
import java.util.List;

public class TopoDaoImpl extends AbstractDaoImpl implements TopoDao {

    public List<Topo> listTopo() {
        String sql = "SELECT publication.name, topo.description, count(spot_id) as spots, topo.publication_id AS topo_id " +
                "FROM topo " +
                "LEFT OUTER JOIN publication ON publication.id = topo.publication_id " +
                "LEFT OUTER JOIN topo_has_spot ON topo_id = topo.publication_id " +
                "GROUP BY topo.publication_id, publication.name " +
                "ORDER BY name ASC;";

        RowMapper<Topo> rowMapper = new TopoRM();

        return getJdbcTemplate().query(sql, rowMapper);
    }

    public void addTopo(Topo topo) {
        String sql = "INSERT INTO publication (user_account_id, name) VALUES (:user_id, :publication_name);" +
                "INSERT INTO topo (publication_id, description) VALUES ((SELECT LASTVAL()), :topo_description);";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", topo.getUserAccountId(), Types.INTEGER);
        args.addValue("publication_name", topo.getName(), Types.VARCHAR);
        args.addValue("topo_description", topo.getDescription(), Types.VARCHAR);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public Topo getTopo(Topo topo) {
        String sql = "SELECT publication.name, topo.description, count(spot_id) as spots, topo.publication_id AS topo_id " +
                "FROM topo, publication, topo_has_spot " +
                "WHERE publication.id = topo.publication_id " +
                "AND topo_has_spot.topo_id = topo.publication_id " +
                "AND topo.publication_id = :publication_id " +
                "GROUP BY topo.publication_id, publication.name;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", topo.getPublicationId(), Types.INTEGER);

        RowMapper<Topo> rowMapper = new TopoRM();

        return getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
    }

    public void updateTopo(Topo topo) {
        String sql = "UPDATE topo SET description = :topo_description WHERE topo.publication_id = :publication_id;" +
                "UPDATE publication SET name = :topo_name, updated_at = now() WHERE publication.id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", topo.getPublicationId(), Types.INTEGER);
        args.addValue("topo_name", topo.getName(), Types.VARCHAR);
        args.addValue("topo_description", topo.getDescription(), Types.VARCHAR);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteTopo(Topo topo) {
        String sql = "DELETE FROM topo WHERE topo.publication_id = :publication_id;" +
                "DELETE FROM publication WHERE publication.id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", topo.getPublicationId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void addTopoHasSpot(Topo topo, Spot spot) {
        String sql = "INSERT INTO topo_has_spot (topo_id, spot_id) VALUES (:topo_id, :spot_id);";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", topo.getPublicationId(), Types.INTEGER);
        args.addValue("spot_id", spot.getPublicationId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public List<Spot> getNotRelatedSpots(Topo topo) {
        String sql = "SELECT publication.name, spot.publication_id AS spot_id " +
                "FROM publication, spot " +
                "WHERE publication.id = spot.publication_id " +
                "AND spot.publication_id NOT IN " +
                "(SELECT spot_id FROM topo_has_spot WHERE topo_id = :topo_id) " +
                "ORDER BY publication.name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", topo.getPublicationId(), Types.INTEGER);

        RowMapper<Spot> rowMapper = new SpotRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public List<Topo> getTopoHasSpot(Topo topo) {
        String sql = "SELECT publication.name, spot.description, topo.publication_id AS topo_id, spot.publication_id AS spot_id " +
                "FROM publication, spot, topo_has_spot, topo " +
                "WHERE publication.id = spot.publication_id " +
                "AND spot.publication_id = topo_has_spot.spot_id " +
                "AND topo_has_spot.topo_id = topo.publication_id " +
                "AND topo.publication_id = :publication_id " +
                "ORDER BY publication.name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", topo.getPublicationId(), Types.INTEGER);

        RowMapper<Topo> rowMapper = new TopoRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public void deleteTopoHastSpot(Topo topo, Spot spot) {
        String sql = "DELETE FROM topo_has_spot WHERE topo_id = :topo_id AND spot_id = :spot_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("topo_id", topo.getPublicationId(), Types.INTEGER);
        args.addValue("spot_id", spot.getPublicationId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }
}
