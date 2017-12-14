package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.SpotDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.SpotRM;
import com.nicolasboueme.climbing.model.entity.Spot;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Types;
import java.util.List;

public class SpotDaoImpl extends AbstractDaoImpl implements SpotDao {

    public List<Spot> listSpots() {
        String sql = "SELECT publication.name, spot.publication_id AS spot_id, spot.description, spot.height FROM publication, spot WHERE publication.id = spot.publication_id;";

        RowMapper<Spot> rowMapper = new SpotRM();

        return getJdbcTemplate().query(sql, rowMapper);
    }

    public void addSpot(Spot spot) {
        String sql = "INSERT INTO publication (user_account_id, name) VALUES (:user_id, :publication_name);" +
                "INSERT INTO spot (publication_id, description, height) VALUES ((SELECT currval('publication_id_seq')), :spot_description, :spot_height);";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", spot.getUserAccountId(), Types.INTEGER);
        args.addValue("publication_name", spot.getName(), Types.VARCHAR);
        args.addValue("spot_description", spot.getDescription(), Types.VARCHAR);
        args.addValue("spot_height", spot.getHeight(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void updateSpot(Spot spot) {
        String sql = "UPDATE spot SET description = :spot_description, height = :spot_height WHERE spot.publication_id = :publication_id;" +
                "UPDATE publication SET name = :spot_name, updated_at = now() WHERE publication.id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", spot.getPublicationId(), Types.INTEGER);
        args.addValue("spot_name", spot.getName(), Types.VARCHAR);
        args.addValue("spot_description", spot.getDescription(), Types.VARCHAR);
        args.addValue("spot_height", spot.getHeight(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteSpot(Spot spot) {
        String sql = "DELETE FROM publication WHERE publication.id " +
                        "IN (SELECT route.publication_id FROM route WHERE route.sector_id " +
                            "IN (SELECT sector.publication_id FROM sector WHERE sector.spot_id = :publication_id));" +
                "DELETE FROM publication WHERE publication.id IN (SELECT sector.publication_id FROM sector WHERE sector.spot_id = :publication_id);" +
                "DELETE FROM publication WHERE publication.id = :publication_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", spot.getPublicationId(), Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }
}
