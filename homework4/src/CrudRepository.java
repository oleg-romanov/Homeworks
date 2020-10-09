
import java.util.List;
import java.util.Optional;

/**
 * 25.09.2020
 * 2. DB
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface CrudRepository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);

    void save(T entity);
    void update(T entity);
    void remove(T entity);
    void removeById(Long id);
}
