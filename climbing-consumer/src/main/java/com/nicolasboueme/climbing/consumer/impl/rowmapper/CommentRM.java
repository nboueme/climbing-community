package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRM implements RowMapper<Comment> {
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (rs.getMetaData().getColumnName(i).equals("id")) comment.setId(rs.getInt("id"));
            else if (rs.getMetaData().getColumnName(i).equals("user_account_id")) comment.setUserAccountId(rs.getInt("user_account_id"));
            else if (rs.getMetaData().getColumnName(i).equals("publication_id")) comment.setPublicationId(rs.getInt("publication_id"));
            else if (rs.getMetaData().getColumnName(i).equals("parent_id")) comment.setParentId(rs.getInt("parent_id"));
            else if (rs.getMetaData().getColumnName(i).equals("created_at")) comment.setCreatedAt(rs.getTimestamp("created_at"));
            else if (rs.getMetaData().getColumnName(i).equals("updated_at")) comment.setUpdatedAt(rs.getTimestamp("updated_at"));
            else if (rs.getMetaData().getColumnName(i).equals("content")) comment.setContent(rs.getString("content"));
        }

        UserAccountRM userRM = new UserAccountRM();
        comment.setAuthor(userRM.mapRow(rs, rowNum));

        return comment;
    }
}
