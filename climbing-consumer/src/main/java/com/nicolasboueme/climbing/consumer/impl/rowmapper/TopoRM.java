package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.Topo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopoRM implements RowMapper<Topo> {
    public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Topo topo = new Topo();
        topo.setName(rs.getString("name"));
        topo.setDescription(rs.getString("description"));
        topo.setPublicationId(rs.getInt("publication_id"));
        return topo;
    }
}
