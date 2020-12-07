package ru.itis.repositories.categoryRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.model.Category;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryJdbcImpl implements CategoryRepository {

    private JdbcTemplate jdbcTemplate;

    public CategoryRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String SQL_FIND_CATEGORY_BY_ID = "select * from category where id = ?";
    private static final String SQL_FIND_ALL = "select * from category";
    private static final String SQL_DELETE_CATEGORY_BY_ID = "delete from category where id = ?";
    private static final String SQL_ADD_NEW_CATEGORY = "insert into category(name, image_id) values (?, ?)";
    private static final String SQL_UPDATE = "update category set name=?, image_id=? where id = ?";

    private RowMapper<Category> categoryRowMapper = (row, rowNumber) -> Category.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .imageId(row.getLong("image_id"))
            .build();

    @Override
    public Optional<Category> findById(Long id) {
        Category category;
        try {
            category = jdbcTemplate.queryForObject(SQL_FIND_CATEGORY_BY_ID, categoryRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            category = null;
        }
        return Optional.ofNullable(category);
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        return jdbcTemplate.update(SQL_DELETE_CATEGORY_BY_ID, id) == 1;
    }

    @Override
    public void save(Category entity) {
        jdbcTemplate.update(SQL_ADD_NEW_CATEGORY, entity.getName(), entity.getImageId());
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, categoryRowMapper);
    }

    @Override
    public Boolean updateCategory(Long id, Category category) {
        return jdbcTemplate.update(SQL_UPDATE, category.getName(), category.getImageId(), id) == 1;
    }
}
