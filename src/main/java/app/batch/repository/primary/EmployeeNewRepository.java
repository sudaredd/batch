package app.batch.repository.primary;

import app.batch.model.EmployeeNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeNewRepository extends JpaRepository<EmployeeNew, Long> {
}
