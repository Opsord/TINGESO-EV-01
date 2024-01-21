package TopEducation.TopEducationApp.repositories;

import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository

// Part of the persistence layer
public interface StudentScoreRepository extends CrudRepository<StudentScoreEntity, Long> {
    // Custom query's here

    // Custom query to find all scores/grades for a given student and
    // save them in a list
    @Query("SELECT e FROM StudentScoreEntity e WHERE e.scoreRUT = :gradeRUT")
    ArrayList<StudentScoreEntity> findAllGradesByStudentRUT(@Param("gradeRUT") String gradeRUT);

}
