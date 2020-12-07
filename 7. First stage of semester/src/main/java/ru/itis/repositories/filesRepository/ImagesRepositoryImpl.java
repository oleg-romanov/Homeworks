package ru.itis.repositories.filesRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.model.Image;

import javax.sql.DataSource;
import java.util.List;

public class ImagesRepositoryImpl implements ImagesRepository {

    //language=SQL
    private final static String SQL_INSERT = "insert into image (name, type, size) " +
            "values (?, ?, ?)";

    //language=SQL
    private final static String SQL_SELECT_BY_ID = "select * from image where id = ?";
    private final static String SQL_SELECT_BY_NAME = "select * from image where name = ?";

    private JdbcTemplate jdbcTemplate;

    public ImagesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Image> fileRowMapper = (row, rowNumber) ->
            Image.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .build();


    @Override
    public void save(Image entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getName(), entity.getType(), entity.getSize());
    }

    @Override
    public Image findById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, fileRowMapper, id);
    }

    @Override
    public Image findByName(String name) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, fileRowMapper, name);
    }

    @Override
    public List<Image> findAll() {
        return null;
    }

}
