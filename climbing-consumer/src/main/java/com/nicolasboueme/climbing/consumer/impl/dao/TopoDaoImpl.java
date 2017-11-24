package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.TopoDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.TopoRM;
import com.nicolasboueme.climbing.model.entity.Topo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;

public class TopoDaoImpl extends AbstractDaoImpl implements TopoDao {

    public List<Topo> listTopo() {
        String sql = "SELECT publication.name, topo.description, topo.publication_id FROM publication, topo WHERE publication.id = topo.publication_id;";

        RowMapper<Topo> rowMapper = new TopoRM();

        return getJdbcTemplate().query(sql, rowMapper);
    }

    public Topo getTopo(int topoId) {
        String sql = "SELECT publication.name, topo.description, topo.publication_id FROM publication, topo WHERE publication.id = topo.publication_id AND topo.publication_id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", topoId);

        RowMapper<Topo> rowMapper = new TopoRM();

        return getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);
    }

}
