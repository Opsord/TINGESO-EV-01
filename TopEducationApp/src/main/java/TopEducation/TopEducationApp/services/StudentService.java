package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.InstallmentEntity;
import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.repositories.StudentRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

// Part of the business layer
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentScoreService studentScoreService;

    @Autowired
    InstallmentService installmentService;

    // Get all the students
    @Generated
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
            StudentEntity student = findById(id);
            ArrayList<InstallmentEntity> installments = installmentService.findAllByInstallmentRUT(student.getRut());
            for (InstallmentEntity installment : installments) {
                installmentService.deleteInstallment(installment.getId());
            }
        } catch (Exception ignored) {
        }
    }

    // Delete all students
    @Generated
    public void deleteAllStudents() {
        try {
            studentRepository.deleteAll();
            installmentService.deleteAllInstallments();
        } catch (Exception ignored) {
        }
    }

    // Find by methods

    // Find by ID
    public StudentEntity findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
