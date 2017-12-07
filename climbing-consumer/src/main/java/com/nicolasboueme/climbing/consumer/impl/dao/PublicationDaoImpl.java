package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.PublicationDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.CommentRM;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Publication;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class PublicationDaoImpl extends AbstractDaoImpl implements PublicationDao {

    public List<Publication> listPublication() {
        /*String sql = "SELECT publication.name FROM publication WHERE publication.name LIKE ?;";

        RowMapper<Publication> rowMapper = new RowMapper<Publication>() {
            public Publication mapRow(ResultSet rs, int rowNum) throws SQLException {
                Publication publication = new Publication();
                publication.setName(rs.getString("name"));
                return publication;
            }
        };

        return getJdbcTemplate().query(sql, rowMapper, "%c%");*/

        return null;
    }

    public List<Comment> getParentsComments(Comment comment) {
        String sql = "SELECT comment.id, image_url, pseudo, content, comment.created_at, comment.updated_at " +
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
        String sql = "SELECT image_url, pseudo, content, comment.parent_id, comment.created_at, comment.updated_at " +
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
}
