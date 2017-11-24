package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.SectorDao;
import com.nicolasboueme.climbing.model.entity.Sector;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SectorDaoImpl extends AbstractDaoImpl implements SectorDao {

    public List<Sector> listSectorsFromParent(int spotId) {
        String sql = "SELECT publication.name, sector.height, sector.publication_id FROM publication, sector WHERE publication.id = sector.publication_id AND sector.spot_id = ?;";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        RowMapper<Sector> rowMapper = new RowMapper<Sector>() {
            public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
                Sector sector = new Sector();
                sector.setName(rs.getString("name"));
                sector.setHeight(rs.getInt("height"));
                sector.setPublicationId(rs.getInt("publication_id"));
                return sector;
            }
        };

        return jdbcTemplate.query(sql, rowMapper, spotId);
    }
}
