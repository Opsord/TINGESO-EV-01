package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.StudentGradeEntity;
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
    public ArrayList<StudentGradeEntity> getAllStudentGrades() {
        return (ArrayList<StudentGradeEntity>) studentGradeRepository.findAll();
    }

    // Save a student grade
    public void saveStudentGrade(StudentGradeEntity studentGrade) {
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
    public StudentGradeEntity findByGradeRut(String gradeRUT) {
        return studentGradeRepository.findByGradeRUT(gradeRUT);
    }

    // Find by score
    public StudentGradeEntity findByScore(int score) {
        return studentGradeRepository.findByScore(score);
    }

    // Find by exam date
    public StudentGradeEntity findByExamDate(LocalDate examDate) {
        return studentGradeRepository.findByExamDate(examDate);
    }

    // Find by student name
    public StudentGradeEntity findByStudentName(String studentName) {
        return studentGradeRepository.findByStudentName(studentName);
    }

    // Find by student last name
    public StudentGradeEntity findByStudentLastName(String studentLastName) {
        return studentGradeRepository.findByStudentLastName(studentLastName);
    }

}
