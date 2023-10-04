package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import TopEducation.TopEducationApp.repositories.StudentGradeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service

public class StudentGradeService {

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    // Get all the student grades
    public ArrayList<StudentScoreEntity> getAllStudentGrades() {
        return (ArrayList<StudentScoreEntity>) studentGradeRepository.findAll();
    }

    // Save a student grade
    public void saveStudentGrade(StudentScoreEntity studentGrade) {
        studentGradeRepository.save(studentGrade);
    }

    // Delete a student grade
    public void deleteStudentGrade(Long id) {
        try {
            studentGradeRepository.deleteById(id);
        } catch (Exception ignored) {
        }
    }

    // Delete all student grades
    public void deleteAllStudentGrades() {
        try {
            studentGradeRepository.deleteAll();
        } catch (Exception ignored) {
        }
    }

    // Find by methods

    // Find by gradeRUT
    public StudentScoreEntity findByGradeRut(String gradeRUT) {
        return studentGradeRepository.findByGradeRUT(gradeRUT);
    }

    // Find by score
    public StudentScoreEntity findByScore(int score) {
        return studentGradeRepository.findByScore(score);
    }

    // Find by exam date
    public StudentScoreEntity findByExamDate(LocalDate examDate) {
        return studentGradeRepository.findByExamDate(examDate);
    }

    // Find by student name
    public StudentScoreEntity findByStudentName(String studentName) {
        return studentGradeRepository.findByStudentName(studentName);
    }

    // Find by student last name
    public StudentScoreEntity findByStudentLastName(String studentLastName) {
        return studentGradeRepository.findByStudentLastName(studentLastName);
    }

    // Find all by RUT
    public ArrayList<StudentScoreEntity> findAllByRUT(String gradeRUT) {
        return studentGradeRepository.findAllGradesByStudentRUT(gradeRUT);
    }
}
