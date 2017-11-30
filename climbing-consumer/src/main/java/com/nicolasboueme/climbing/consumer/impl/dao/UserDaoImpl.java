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

    public UserAccount userLogin(String login, String password) {
        String sql = "SELECT * FROM user_account WHERE email = :user_login OR pseudo = :user_login;";

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_login", login, Types.VARCHAR);

        RowMapper<UserAccount> rowMapper = new UserAccountRM();

        try {
            UserAccount user = getNamedParameterJdbcTemplate().queryForObject(sql, args, rowMapper);

            if (BCrypt.checkpw(password, user.getPassword())) {
                System.out.println("Correct login credentials");
                return user;
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

    public void addUser(UserAccount user) {
        String sql = "INSERT INTO user_account (pseudo, email, password) VALUES (:user_pseudo, :user_email, :user_password);";

        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        MapSqlParameterSource args = new MapSqlParameterSource();
        args.addValue("user_pseudo", user.getPseudo(), Types.VARCHAR);
        args.addValue("user_email", user.getEmail(), Types.VARCHAR);
        args.addValue("user_password", hashed, Types.VARCHAR);

        try {
            getNamedParameterJdbcTemplate().update(sql, args);
        } catch (DuplicateKeyException exception) {
            System.out.println(exception.getMessage());
        }
    }

}
