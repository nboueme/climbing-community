package com.nicolasboueme.climbing.consumer.impl.dao;

import com.nicolasboueme.climbing.consumer.contract.dao.UserDao;
import com.nicolasboueme.climbing.consumer.impl.rowmapper.UserAccountRM;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.sql.Types;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    public void addUser(UserAccount user) {
        String sql = "INSERT INTO user_account (pseudo, email, password, image_url) VALUES (:user_pseudo, :user_email, :user_password, :user_image);";

        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_pseudo", user.getPseudo(), Types.VARCHAR);
        args.addValue("user_email", user.getEmail(), Types.VARCHAR);
        args.addValue("user_password", hashed, Types.VARCHAR);
        args.addValue("user_image", "/image/user/user-0.png", Types.VARCHAR);

        try {
            getNamedParameterJdbcTemplate().update(sql, args);
        } catch (DuplicateKeyException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public UserAccount getUser(UserAccount user) {
        String sql = "SELECT * FROM user_account WHERE email = :user_login AND role != :not_robot;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_login", user.getEmail(), Types.VARCHAR);
        args.addValue("not_robot", "robot", Types.VARCHAR);

        RowMapper<UserAccount> rowMapper = new UserAccountRM();

        try {
            UserAccount userQuery = getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);

            if (BCrypt.checkpw(user.getPassword(), userQuery.getPassword())) {
                System.out.println("Correct login credentials");
                return userQuery;
            }
            else {
                System.out.println("Incorrect login credentials");
                return null;
            }

        } catch (EmptyResultDataAccessException exception) {
            System.out.println("Incorrect login credentials");
            return null;
        }
    }

    public void updateUser(UserAccount user) {
        String sql = "UPDATE user_account SET pseudo = :user_pseudo, email = :user_email, password = :user_password, updated_at = now() WHERE user_account.id = :user_id;";

        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", user.getId(), Types.INTEGER);
        args.addValue("user_pseudo", user.getPseudo(), Types.VARCHAR);
        args.addValue("user_email", user.getEmail(), Types.VARCHAR);
        args.addValue("user_password", hashed, Types.VARCHAR);

        getNamedParameterJdbcTemplate().update(sql, args);
    }

    public void deleteUser(UserAccount user) {
        String sql = "UPDATE publication SET user_account_id = :user_robot WHERE user_account_id = :user_id;" +
                "UPDATE comment SET user_account_id = :user_robot WHERE user_account_id = :user_id;" +
                "DELETE FROM user_has_topo WHERE user_id = :user_id;" +
                "DELETE FROM user_account WHERE user_account.id = :user_id;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_id", user.getId(), Types.INTEGER);
        args.addValue("user_robot", 0, Types.INTEGER);

        getNamedParameterJdbcTemplate().update(sql, args);
    }
}
