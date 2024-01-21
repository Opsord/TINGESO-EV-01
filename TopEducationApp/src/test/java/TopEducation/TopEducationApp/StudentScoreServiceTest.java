package TopEducation.TopEducationApp;

import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import TopEducation.TopEducationApp.services.StudentScoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentScoreServiceTest {

    @Autowired
    StudentScoreService studentScoreService;

    @Test
        // Test for saveStudentScore, deleteStudentGrade, and findById methods
    void saveAndDeleteStudentScore() {
        StudentScoreEntity studentScore = new StudentScoreEntity();
        studentScore.setScoreRUT("99.999.999-K");
        studentScore.setStudentName("Aquiles");
        studentScore.setStudentLastName("Baeza");
        studentScore.setScore(800);
        studentScore.setExamDate(java.time.LocalDate.now());

        // Save the student grade
        studentScoreService.saveStudentScore(studentScore);

        // Verify that the student grade was saved
        StudentScoreEntity savedStudentScore = studentScoreService.findById(studentScore.getId());
        assert (savedStudentScore.getScoreRUT().equals("99.999.999-K"));
        assert (savedStudentScore.getStudentName().equals("Aquiles"));
        assert (savedStudentScore.getStudentLastName().equals("Baeza"));
        assert (savedStudentScore.getScore() == 800);
        assert (savedStudentScore.getExamDate().equals(java.time.LocalDate.now()));

        // Delete the student grade
        studentScoreService.deleteStudentGrade(studentScore.getId());

        // Verify that the student grade was deleted
        StudentScoreEntity deletedStudentScore = studentScoreService.findById(studentScore.getId());
        assert (deletedStudentScore == null);
    }

    @Test
        // Test for getAllStudentGradesByStudentRUT method
    void getAllStudentGradesByStudentRUT() {
        StudentScoreEntity studentScore1 = new StudentScoreEntity();
        studentScore1.setScoreRUT("99.999.999-K");
        studentScore1.setStudentName("Aquiles");
        studentScore1.setStudentLastName("Baeza");
        studentScore1.setScore(800);
        studentScore1.setExamDate(java.time.LocalDate.now());

        StudentScoreEntity studentScore2 = new StudentScoreEntity();
        studentScore2.setScoreRUT("99.999.999-K");
        studentScore2.setStudentName("Aquiles");
        studentScore2.setStudentLastName("Baeza");
        studentScore2.setScore(810);
        studentScore2.setExamDate(java.time.LocalDate.now());

        // Save the student grades
        studentScoreService.saveStudentScore(studentScore1);
        studentScoreService.saveStudentScore(studentScore2);

        // Verify that the student grades were saved
        assert (studentScoreService.getAllStudentGradesByStudentRUT("99.999.999-K").size() == 2);

        // Delete the student grades
        studentScoreService.deleteStudentGrade(studentScore1.getId());
        studentScoreService.deleteStudentGrade(studentScore2.getId());
    }

}
