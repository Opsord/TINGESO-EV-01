package TopEducation.TopEducationApp.repositories;

import TopEducation.TopEducationApp.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

// Part of the persistence layer
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    // Custom query's here

    // FindBy methods

    // Find by RUT
    StudentEntity findByRut(String rut);

    // Find by first name
    StudentEntity findByFirstName(String firstName);

    // Find by last name
    StudentEntity findByLastName(String lastName);

    // Find by birthdate
    StudentEntity findByBirthDate(LocalDate birthDate);

    // Find by school type
    // Type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
    StudentEntity findBySchoolType(int schoolType);

    // Find by school name
    StudentEntity findBySchoolName(String schoolName);

    // Find by graduation year
    StudentEntity findByGraduationYear(int graduationYear);

}
