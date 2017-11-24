package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.TopoDao;
import com.nicolasboueme.climbing.model.entity.Topo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TopoDaoImpl extends AbstractDaoImpl implements TopoDao {

    public List<Topo> listTopo() {
        String sql = "SELECT publication.name, topo.description, topo.publication_id FROM publication, topo WHERE publication.id = topo.publication_id;";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        RowMapper<Topo> rowMapper = new RowMapper<Topo>() {
            public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Topo topo = new Topo();
                topo.setName(rs.getString("name"));
                topo.setDescription(rs.getString("description"));
                topo.setPublicationId(rs.getInt("publication_id"));
                return topo;
            }
        };

        return jdbcTemplate.query(sql, rowMapper);
    }

    public Topo getTopo(int topoId) {
        String sql = "SELECT publication.name, topo.description FROM publication, topo WHERE publication.id = topo.publication_id AND topo.publication_id = ?;";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        RowMapper<Topo> rowMapper = new RowMapper<Topo>() {
            public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Topo topo = new Topo();
                topo.setName(rs.getString("name"));
                topo.setDescription(rs.getString("description"));
                return topo;
            }
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, topoId);
    }
}
