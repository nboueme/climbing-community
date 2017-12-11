package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.Topo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopoRM implements RowMapper<Topo> {
    public Topo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Topo topo = new Topo();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (rs.getMetaData().getColumnName(i).equals("name")) topo.setName(rs.getString("name"));
            else if (rs.getMetaData().getColumnName(i).equals("description")) topo.setDescription(rs.getString("description"));
            else if (rs.getMetaData().getColumnName(i).equals("topo_id")) topo.setPublicationId(rs.getInt("topo_id"));
            else if (rs.getMetaData().getColumnName(i).equals("spots")) topo.setSpotsNumber(rs.getInt("spots"));
        }

        SpotRM spotRM = new SpotRM();
        topo.setTopoHasSpot(spotRM.mapRow(rs, rowNum));

        return topo;
    }
}
