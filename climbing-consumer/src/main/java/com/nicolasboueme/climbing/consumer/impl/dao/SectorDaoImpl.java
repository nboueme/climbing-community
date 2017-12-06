package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.SectorDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.SectorRM;
import com.nicolasboueme.climbing.model.entity.Sector;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Types;
import java.util.List;

public class SectorDaoImpl extends AbstractDaoImpl implements SectorDao {

    public List<Sector> listSectorsFromParent(Sector sector) {
        String sql = "SELECT publication.name, sector.publication_id, sector.spot_id, sector.height FROM publication, sector WHERE publication.id = sector.publication_id AND sector.spot_id = :spot_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("spot_id", sector.getSpotId(), Types.INTEGER);

        RowMapper<Sector> rowMapper = new SectorRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

}
