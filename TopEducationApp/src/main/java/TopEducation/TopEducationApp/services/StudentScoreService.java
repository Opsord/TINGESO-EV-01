package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import TopEducation.TopEducationApp.repositories.StudentScoreRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class StudentScoreService {

    @Autowired
    private StudentScoreRepository studentScoreRepository;

    // Get all the student grades
    @Generated
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

    // Find a student grade by id
    public StudentScoreEntity findById(Long id) {
        return studentScoreRepository.findById(id).orElse(null);
    }

    // Delete all student grades
    @Generated
    public void deleteAllStudentGrades() {
        try {
            studentScoreRepository.deleteAll();
        } catch (Exception ignored) {
        }
    }

    // Get all the student grades by student RUT
    public ArrayList<StudentScoreEntity> getAllStudentGradesByStudentRUT(String gradeRUT) {
        return studentScoreRepository.findAllGradesByStudentRUT(gradeRUT);
    }

}
