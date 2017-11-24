package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.SpotDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.SpotRM;
import com.nicolasboueme.climbing.model.entity.Spot;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class SpotDaoImpl extends AbstractDaoImpl implements SpotDao {

    public List<Spot> listSpots() {
        String sql = "SELECT publication.name, spot.height, spot.publication_id FROM publication, spot WHERE publication.id = spot.publication_id;";

        RowMapper<Spot> rowMapper = new SpotRM();

        return getJdbcTemplate().query(sql, rowMapper);
    }

}
