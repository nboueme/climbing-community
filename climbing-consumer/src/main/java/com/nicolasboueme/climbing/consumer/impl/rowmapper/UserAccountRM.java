package com.nicolasboueme.climbing.consumer.impl.rowmapper;

import com.nicolasboueme.climbing.model.entity.UserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountRM implements RowMapper<UserAccount> {
    public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAccount user = new UserAccount();
        user.setId(rs.getInt("id"));
        user.setPseudo(rs.getString("pseudo"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setImageUrl("image_url");
        user.setRole(rs.getString("role"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));
        return user;
    }
}
