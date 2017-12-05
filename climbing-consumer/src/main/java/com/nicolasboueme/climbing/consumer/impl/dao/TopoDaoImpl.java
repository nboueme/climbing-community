package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.TopoDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.TopoRM;
import com.nicolasboueme.climbing.model.entity.Topo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Types;
import java.util.List;

public class TopoDaoImpl extends AbstractDaoImpl implements TopoDao {

    public List<Topo> listTopo() {
        String sql = "SELECT publication.name, topo.description, topo.publication_id FROM publication, topo WHERE publication.id = topo.publication_id ORDER BY name ASC;";

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
        String sql = "SELECT publication.name, topo.description, topo.publication_id FROM publication, topo WHERE publication.id = topo.publication_id AND topo.publication_id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", topo.getPublicationId(), Types.INTEGER);

        RowMapper<Topo> rowMapper = new TopoRM();

        return getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
    }

    public void updateTopo(Topo topo) {
        String sql = "UPDATE topo SET description = :topo_description WHERE topo.publication_id = :publication_id;" +
                "UPDATE publication SET name = :topo_name WHERE publication.id = :publication_id;";

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

}
