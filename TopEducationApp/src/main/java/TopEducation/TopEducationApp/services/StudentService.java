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

    // Find by RUT
    public StudentEntity findByRut(String rut) {
        return studentRepository.findByRut(rut);
    }

    // Find by first name
    public StudentEntity findByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    // Find by last name
    public StudentEntity findByLastName(String lastName) {
        return studentRepository.findByLastName(lastName);
    }

    // Find by birthdate
    public StudentEntity findByBirthDate(String birthDate) {
        return studentRepository.findByBirthDate(LocalDate.parse(birthDate));
    }

    // Find by school type
    // Type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
    public StudentEntity findBySchoolType(int schoolType) {
        return studentRepository.findBySchoolType(schoolType);
    }

    // Find by school name
    public StudentEntity findBySchoolName(String schoolName) {
        return studentRepository.findBySchoolName(schoolName);
    }

    // Find by graduation year
    public StudentEntity findByGraduationYear(int graduationYear) {
        return studentRepository.findByGraduationYear(graduationYear);
    }

    // Find by exams taken
    public StudentEntity findByExamsTaken(int examsTaken) {
        return studentRepository.findByExamsTaken(examsTaken);
    }

    // Find by average score
    public StudentEntity findByAverageGrade(int averageGrade) {
        return studentRepository.findByAverageGrade(averageGrade);
    }

    // Find by payment method
    public StudentEntity findByPaymentMethod(String paymentMethod) {
        return studentRepository.findByPaymentMethod(paymentMethod);
    }

    // Find by agreed installments
    public StudentEntity findByAgreedInstallments(int agreedInstallments) {
        return studentRepository.findByAgreedInstallments(agreedInstallments);
    }

    // Find by installments paid
    public StudentEntity findByInstallmentsPaid(int installmentsPaid) {
        return studentRepository.findByInstallmentsPaid(installmentsPaid);
    }

    // Find by overdue installments
    public StudentEntity findByOverdueInstallments(int overdueInstallments) {
        return studentRepository.findByOverdueInstallments(overdueInstallments);
    }

    // Find by last payment date
    public StudentEntity findByLastPaymentDate(String lastPaymentDate) {
        return studentRepository.findByLastPaymentDate(LocalDate.parse(lastPaymentDate));
    }

    // Find by total amount to pay
    public StudentEntity findByTotalAmountToPay(int totalAmountToPay) {
        return studentRepository.findByTotalAmountToPay(totalAmountToPay);
    }

    // Find by total amount paid
    public StudentEntity findByTotalAmountPaid(int totalAmountPaid) {
        return studentRepository.findByTotalAmountPaid(totalAmountPaid);
    }
}
