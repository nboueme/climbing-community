package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.UserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountRM implements RowMapper<UserAccount> {
    public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAccount user = new UserAccount();

        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            if (rs.getMetaData().getColumnName(i).equals("id")) user.setId(rs.getInt("id"));
            else if (rs.getMetaData().getColumnName(i).equals("pseudo")) user.setPseudo(rs.getString("pseudo"));
            else if (rs.getMetaData().getColumnName(i).equals("email")) user.setEmail(rs.getString("email"));
            else if (rs.getMetaData().getColumnName(i).equals("password")) user.setPassword(rs.getString("password"));
            else if (rs.getMetaData().getColumnName(i).equals("image_url")) user.setImageUrl(rs.getString("image_url"));
            else if (rs.getMetaData().getColumnName(i).equals("role")) user.setRole(rs.getString("role"));
            else if (rs.getMetaData().getColumnName(i).equals("created_at")) user.setCreatedAt(rs.getTimestamp("created_at"));
            else if (rs.getMetaData().getColumnName(i).equals("updated_at")) user.setUpdatedAt(rs.getTimestamp("updated_at"));
        }

        return user;
    }
}
