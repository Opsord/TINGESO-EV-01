package TopEducation.TopEducationApp.repositories;

import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository

// Part of the persistence layer
public interface StudentScoreRepository extends CrudRepository<StudentScoreEntity, Long> {
    // Custom query's here

    // Custom query to find all scores/grades for a given student and
    // save them in a list
    @Query("SELECT e FROM StudentScoreEntity e WHERE e.gradeRUT = :gradeRUT")
    ArrayList<StudentScoreEntity> findAllGradesByStudentRUT(@Param("gradeRUT") String gradeRUT);

    // FindBy methods

    // Find by studentRut
    StudentScoreEntity findByGradeRUT(String gradeRUT);

    // Find by grade
    StudentScoreEntity findByScore(double score);

    // Find by exam date
    StudentScoreEntity findByExamDate(LocalDate examDate);

    // Find by student name
    StudentScoreEntity findByStudentName(String studentName);

    // Find by student last name
    StudentScoreEntity findByStudentLastName(String studentLastName);

}
