package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import TopEducation.TopEducationApp.repositories.StudentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    // Find by methods

    // Find by gradeRUT
    public StudentScoreEntity findByGradeRut(String gradeRUT) {
        return studentScoreRepository.findByGradeRUT(gradeRUT);
    }

    // Find by score
    public StudentScoreEntity findByScore(int score) {
        return studentScoreRepository.findByScore(score);
    }

    // Find by exam date
    public StudentScoreEntity findByExamDate(LocalDate examDate) {
        return studentScoreRepository.findByExamDate(examDate);
    }

    // Find by student name
    public StudentScoreEntity findByStudentName(String studentName) {
        return studentScoreRepository.findByStudentName(studentName);
    }

    // Find by student last name
    public StudentScoreEntity findByStudentLastName(String studentLastName) {
        return studentScoreRepository.findByStudentLastName(studentLastName);
    }

    // Find all by RUT
    public ArrayList<StudentScoreEntity> findAllByRUT(String gradeRUT) {
        return studentScoreRepository.findAllGradesByStudentRUT(gradeRUT);
    }

    // Count taken exams
    public int TakenExams(String gradeRUT) {
        return studentScoreRepository.findAllGradesByStudentRUT(gradeRUT).size();
    }
}
