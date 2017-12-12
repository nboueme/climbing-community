package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.PublicationDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.*;
import com.nicolasboueme.climbing.model.entity.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.Types;
import java.util.List;

public class PublicationDaoImpl extends AbstractDaoImpl implements PublicationDao {

    public List<Spot> listPublication(Spot spot) {
        String sql = "SELECT publication_id AS spot_id, name, description, height " +
                "FROM publication, spot " +
                "WHERE publication.id = spot.publication_id " +
                "AND (LOWER(name) LIKE LOWER('%c%') OR LOWER(description) LIKE LOWER('%c%')) " +
                "AND (height <= :publication_height OR height ISNULL) " +
                "ORDER BY name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_text", "%" + spot.getName() + "%", Types.VARCHAR);
        args.addValue("publication_height", spot.getHeight(), Types.INTEGER);

        RowMapper<Spot> rowMapper = new SpotRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public List<Sector> listPublication(Sector sector) {
        String sql = "SELECT publication_id, spot_id, name, height " +
                "FROM publication, sector " +
                "WHERE publication.id = sector.publication_id " +
                "AND LOWER(name) LIKE LOWER(:publication_text) " +
                "AND (height <= :publication_height OR height ISNULL) " +
                "ORDER BY name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_text", "%" + sector.getName() + "%", Types.VARCHAR);
        args.addValue("publication_height", sector.getHeight(), Types.INTEGER);

        RowMapper<Sector> rowMapper = new SectorRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public List<Route> listPublication(Route route) {
        String sql = "SELECT publication_id, parent_publication_id, name, height, quotation, points_number " +
                "FROM publication, route " +
                "WHERE publication.id = route.publication_id " +
                "AND LOWER(name) LIKE LOWER(:publication_text) " +
                "AND (height <= :publication_height OR height ISNULL) " +
                "AND quotation LIKE :publication_quotation " +
                "AND (points_number >= :publication_points OR  points_number ISNULL) " +
                "ORDER BY name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_text", "%" + route.getName() + "%", Types.VARCHAR);
        args.addValue("publication_height", route.getHeight(), Types.INTEGER);
        args.addValue("publication_quotation", route.getQuotation(), Types.VARCHAR);
        args.addValue("publication_points", route.getPointsNumber(), Types.INTEGER);

        RowMapper<Route> rowMapper = new RouteRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public List<Topo> listPublication(Topo topo) {
        String sql = "SELECT publication_id AS topo_id, name, description " +
                "FROM publication, topo " +
                "WHERE publication.id = topo.publication_id " +
                "AND (LOWER(name) LIKE LOWER('%c%') OR LOWER(description) LIKE LOWER('%c%')) " +
                "ORDER BY name ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_text", "%" + topo.getName() + "%", Types.VARCHAR);

        RowMapper<Topo> rowMapper = new TopoRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public void addComment(Comment comment) {
        String sql;

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", comment.getUserAccountId(), Types.INTEGER);
        args.addValue("publication_id", comment.getPublicationId(), Types.INTEGER);
        args.addValue("comment_content", comment.getContent(), Types.VARCHAR);

        if (comment.getParentId() != 0) {
            sql = "INSERT INTO comment (user_account_id, publication_id, parent_id, content)" +
                    "VALUES (:user_id, :publication_id, :parent_id, :comment_content)";
            args.addValue("parent_id", comment.getParentId(), Types.INTEGER);
        } else {
            sql = "INSERT INTO comment (user_account_id, publication_id, content)" +
                    "VALUES (:user_id, :publication_id, :comment_content)";
        }

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public List<Comment> getParentsComments(Comment comment) {
        String sql = "SELECT comment.id, comment.user_account_id, image_url, pseudo, content, comment.created_at, comment.updated_at " +
                "FROM publication, comment, user_account " +
                "WHERE publication.id = comment.publication_id " +
                "AND comment.user_account_id = user_account.id " +
                "AND comment.publication_id = :publication_id " +
                "AND comment.parent_id IS NULL " +
                "ORDER BY comment.created_at ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", comment.getPublicationId(), Types.INTEGER);

        RowMapper<Comment> rowMapper = new CommentRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public List<Comment> getChildrenComments(Comment comment) {
        String sql = "SELECT comment.id, comment.parent_id, comment.user_account_id, image_url, pseudo, content, comment.created_at, comment.updated_at " +
                "FROM publication, comment, user_account " +
                "WHERE publication.id = comment.publication_id " +
                "AND comment.user_account_id = user_account.id " +
                "AND comment.publication_id = :publication_id " +
                "AND comment.parent_id IS NOT NULL " +
                "ORDER BY comment.created_at ASC;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("publication_id", comment.getPublicationId(), Types.INTEGER);

        RowMapper<Comment> rowMapper = new CommentRM();

        return getNamedParameterJdbcTemplate().query(sql, args, rowMapper);
    }

    public void updateComment(Comment comment) {
        String sql = "UPDATE comment SET content = :comment_content, updated_at = now() WHERE comment.id = :comment_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("comment_id", comment.getId(), Types.INTEGER);
        args.addValue("comment_content", comment.getContent(), Types.VARCHAR);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteComment(Comment comment) {
        String sql;

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("comment_id", comment.getId(), Types.INTEGER);

        if (comment.getParentId() != 0) {
            sql = "DELETE FROM comment WHERE comment.parent_id = :parent_id;" +
                    "DELETE FROM comment WHERE comment.id = :comment_id;";
            args.addValue("parent_id", comment.getParentId(), Types.INTEGER);
        } else {
            sql = "DELETE FROM comment WHERE comment.id = :comment_id;";
        }

        getNamedParameterJdbcTemplate().update(sql, args);
    }
}
