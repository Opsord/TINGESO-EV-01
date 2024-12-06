package TopEducation.TopEducationApp.repositories;

import TopEducation.TopEducationApp.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

// Part of the persistence layer
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    // Custom query's here

}
