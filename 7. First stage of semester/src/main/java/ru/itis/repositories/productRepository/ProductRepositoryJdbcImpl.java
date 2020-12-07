package ru.itis.repositories.productRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.model.Product;
import ru.itis.repositories.categoryRepository.CategoryRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryJdbcImpl implements ProductRepository {

    //language=SQL
    private static final String SQL_SELECT_All = "select * from product";
    private static final String SQL_SELECT_PRODUCTS_BY_CATEGORY = "select * from product where product_category = ?";
    private static final String SQL_SELECT_BY_ID = "select * from product where id = ?";
    private static final String SQL_ADD_NEW_PRODUCT = "insert into product(product_name, product_category, descriptions, image_id) values (?, ?, ?, ?)";
    private static final String SQL_DELETE_PRODUCT_BY_ID = "delete from product where id = ?";
    private static final String SQL_UPDATE = "update product set product_name=?, product_category=?, descriptions=?, image_id=? where id = ?";

    private JdbcTemplate jdbcTemplate;

    public ProductRepositoryJdbcImpl(DataSource dataSource, CategoryRepository categoryRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Product> productRowMapper = (row, rowNumber) -> Product.builder()
            .id(row.getLong("id"))
            .name(row.getString("product_name"))
            .categoryId(row.getLong("product_category"))
            .description(row.getString("descriptions"))
            .imageId(row.getLong("image_id"))
            .build();

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SQL_SELECT_All, productRowMapper);
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        return jdbcTemplate.query(SQL_SELECT_PRODUCTS_BY_CATEGORY, productRowMapper, categoryId);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        Product product;
        try {
            product = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, productRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            product = null;
        }
        return Optional.ofNullable(product);
    }

    @Override
    public Boolean deleteProductById(Long id) {
        return jdbcTemplate.update(SQL_DELETE_PRODUCT_BY_ID, id) == 1;
    }

    @Override
    public void updateProduct(Long id, Product product) {
        boolean a = jdbcTemplate.update(SQL_UPDATE,
                product.getName(),
                product.getCategoryId(),
                product.getDescription(),
                product.getImageId(),
                id) == 1;
        System.out.println(a);
    }

    @Override
    public void save(Product entity) {
        jdbcTemplate.update(SQL_ADD_NEW_PRODUCT,
                entity.getName(),
                entity.getCategoryId(),
                entity.getDescription(),
                entity.getImageId());
    }
}
