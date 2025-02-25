package app.model.repository;

import app.model.entity.Father;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatherRepository extends JpaRepository<Father, Long> {
    Father getFatherById(int id);
}
