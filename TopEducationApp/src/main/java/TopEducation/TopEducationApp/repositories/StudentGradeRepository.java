package TopEducation.TopEducationApp.repositories;

import TopEducation.TopEducationApp.entities.StudentGradeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

// Part of the persistence layer
public interface StudentGradeRepository extends CrudRepository<StudentGradeEntity, Long> {
    // Custom query's here

    // FindBy methods

    // Find by studentRut
    StudentGradeEntity findByGradeRUT(String gradeRUT);

    // Find by grade
    StudentGradeEntity findByScore(double score);

    // Find by exam date
    StudentGradeEntity findByExamDate(LocalDate examDate);

    // Find by student name
    StudentGradeEntity findByStudentName(String studentName);

    // Find by student last name
    StudentGradeEntity findByStudentLastName(String studentLastName);

}
