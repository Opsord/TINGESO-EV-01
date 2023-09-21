package TINGESO.TopEducation.repositories;

//import org.springframework.data.repository.CrudRepository; (not used)
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import TINGESO.TopEducation.entities.StudentEntity;

@Repository

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    //Custom query's here
}