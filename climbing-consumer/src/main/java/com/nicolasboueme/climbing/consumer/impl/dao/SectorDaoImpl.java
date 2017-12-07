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

    public void addSector(Sector sector) {
        String sql = "INSERT INTO publication (user_account_id, name) VALUES (:user_id, :publication_name);" +
                "INSERT INTO sector (publication_id, spot_id, height) VALUES ((SELECT LASTVAL()), :spot_id, :sector_height);";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", sector.getUserAccountId(), Types.INTEGER);
        args.addValue("publication_name", sector.getName(), Types.VARCHAR);
        args.addValue("spot_id", sector.getSpotId(), Types.INTEGER);
        args.addValue("sector_height", sector.getHeight(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void updateSector(Sector sector) {
        String sql = "UPDATE sector SET height = :sector_height WHERE sector.publication_id = :publication_id;" +
                "UPDATE publication SET name = :sector_name, updated_at = now() WHERE publication.id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", sector.getPublicationId(), Types.INTEGER);
        args.addValue("sector_name", sector.getName(), Types.VARCHAR);
        args.addValue("sector_height", sector.getHeight(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteSector(Sector sector) {
        String sql = "DELETE FROM sector WHERE sector.publication_id = :publication_id;" +
                "DELETE FROM publication WHERE publication.id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", sector.getPublicationId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }
}
