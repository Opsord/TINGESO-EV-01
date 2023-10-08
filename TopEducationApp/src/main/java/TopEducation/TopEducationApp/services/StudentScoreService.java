package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import TopEducation.TopEducationApp.repositories.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class StudentScoreService {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    // Get all the student grades
    public ArrayList<StudentScoreEntity> getAllStudentGrades() {
        return (ArrayList<StudentScoreEntity>) studentScoreRepository.findAll();
    }

    // Save a student grade
    public void saveStudentScore(StudentScoreEntity studentGrade) {
        studentScoreRepository.save(studentGrade);
    }

    // Delete a student grade
    public void deleteStudentGrade(Long id) {
        try {
            studentScoreRepository.deleteById(id);
        } catch (Exception ignored) {
        }
    }

    // Delete all student grades
    public void deleteAllStudentGrades() {
        try {
            studentScoreRepository.deleteAll();
        } catch (Exception ignored) {
        }
    }

}
