package ru.itis.repositories.usersRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.model.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final static String SQL_INSERT = "insert into shop_user(first_name, last_name, email, phone_number, hashpassword, is_admin) " +
            "values (?, ?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_BY_EMAIL_FROM_SHOP_USER = "select * from shop_user where email = ?";
    private final static String SQL_UPDATE_EMAIL = "update shop_user set email = ? where email = ?";
    private final static String SQL_UPDATE_IMAGE_ID = "update shop_user set avatar_image_id = ? where id = ?";
    private final static String SQL_UPDATE_NAME = "update shop_user set first_name = ? where first_name = ?";
    private final static String SQL_UPDATE_SURNAME = "update shop_user set last_name = ? where last_name = ?";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .phoneNumber(row.getString("phone_number"))
            .hashPassword(row.getString("hashpassword"))
            .imageId(row.getLong("avatar_image_id"))
            .isAdmin(row.getBoolean("is_admin"))
            .build();


    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getHashPassword(),
                entity.getIsAdmin()
        );
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Boolean updateImageId(Long userId, Long imageId) {
        boolean isComplete = false;
        int count = jdbcTemplate.update(SQL_UPDATE_IMAGE_ID, imageId, userId);
        if (count > 0) {
            isComplete = true;
        }
        return isComplete;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL_FROM_SHOP_USER, userRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }
        return Optional.ofNullable(user);
     }

    @Override
    public Boolean updateEmail(String oldEmail, String newEmail) {
        boolean isComplete = false;
        int count = jdbcTemplate.update(SQL_UPDATE_EMAIL, newEmail, oldEmail);
        if (count > 0) {
            isComplete = true;
        }
        return isComplete;
    }

    @Override
    public Boolean updateName(String oldName, String newName) {
        boolean isComplete = false;
        int count = jdbcTemplate.update(SQL_UPDATE_NAME,newName, oldName);
        if (count > 0) {
            isComplete = true;
        }
        return isComplete;
    }

    @Override
    public Boolean updateSurname(String oldSurname, String newSurname) {
        boolean isComplete = false;
        int count = jdbcTemplate.update(SQL_UPDATE_SURNAME, newSurname, oldSurname);
        if (count > 0) {
            isComplete = true;
        }
        return isComplete;
    }

}
