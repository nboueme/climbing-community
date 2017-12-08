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
