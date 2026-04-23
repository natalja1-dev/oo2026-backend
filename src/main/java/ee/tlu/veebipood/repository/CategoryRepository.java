package ee.tlu.veebipood.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ee.tlu.veebipood.entity.Category;

// Non-null type argument is expected
// Spring boot 4.0

public interface CategoryRepository extends JpaRepository<@NonNull Category,@NonNull Long> {
}
