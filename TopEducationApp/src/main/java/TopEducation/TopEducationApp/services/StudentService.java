package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service

// Part of the business layer
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentScoreService studentScoreService;

    // Get all the students
    public ArrayList<StudentEntity> getAllStudents() {
        return (ArrayList<StudentEntity>) studentRepository.findAll();
    }

    // Save a student
    public void saveStudent(StudentEntity student) {
        studentRepository.save(student);
    }

    // Delete a student
    public void deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (Exception ignored) {
        }
    }

    // Delete all students
    public void deleteAllStudents() {
        try {
            studentRepository.deleteAll();
        } catch (Exception ignored) {
        }
    }

    // Find by methods

    // Find by ID
    public StudentEntity findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

}
