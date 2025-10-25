package com.gopnik.userservice.dao;

import com.gopnik.userservice.appuser.AppUser;
import com.gopnik.userservice.appuser.AppUserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UserDao {

    @Autowired
    @Qualifier("datasourceH2")
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<AppUser> rowMapper = (rs, rowNum) -> {
        AppUser user = new AppUser();
        user.setId(rs.getLong("ID"));
        user.setFirstName(rs.getString("FIRST_NAME"));
        user.setLastName(rs.getString("LAST_NAME"));
        user.setEmail(rs.getString("EMAIL"));
        user.setAppUserRole(AppUserRole.fromString(rs.getString("APP_USER_ROLE")));
        user.setIsAccountEnabled(rs.getBoolean("IS_ACCOUNT_ENABLED"));
        return user;
    };

    public List<AppUser> findAllUserWithGivenFirstName(String firstName) {
        log.debug("Fetching all the users having first name like {}", firstName);
        String sql = "SELECT * FROM app_user WHERE first_name LIKE ?";
        return jdbcTemplate.query(sql, rowMapper, firstName + "%");
    }

    public Optional<AppUser> findByEmail(String email) {
        String sql = "SELECT * FROM app_user WHERE email = ?";
        List<AppUser> users = jdbcTemplate.query(sql, rowMapper, email);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }
}
