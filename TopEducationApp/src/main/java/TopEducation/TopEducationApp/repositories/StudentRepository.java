package TopEducation.TopEducationApp.repositories;

import TopEducation.TopEducationApp.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.JpaRepository;

@Repository

//Part of the persistence layer
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    //Custom query's here
}
