package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.PublicationDao;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Publication;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<Comment> getCommentsFromPublication() {
        return null;
    }
}
