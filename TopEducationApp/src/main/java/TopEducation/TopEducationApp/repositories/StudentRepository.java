package TopEducation.TopEducationApp.repositories;

import TopEducation.TopEducationApp.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

// Part of the persistence layer
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    // Custom query's here

    // FindBy methods

    // Find by RUT
    StudentEntity findByRut(String rut);

    // Find by first name
    StudentEntity findByFirstName(String firstName);

    // Find by last name
    StudentEntity findByLastName(String lastName);

    // Find by birthdate
    StudentEntity findByBirthDate(LocalDate birthDate);

    // Find by school type
    // Type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
    StudentEntity findBySchoolType(int schoolType);

    // Find by school name
    StudentEntity findBySchoolName(String schoolName);

    // Find by graduation year
    StudentEntity findByGraduationYear(int graduationYear);

    // Find by exams taken
    StudentEntity findByExamsTaken(int examsTaken);

    // Find by average score
    StudentEntity findByAverageGrade(int averageGrade);

    // Find by payment method
    StudentEntity findByPaymentMethod(String paymentMethod);

    // Find by agreed installments
    StudentEntity findByAgreedInstallments(int agreedInstallments);

    // Find by installments paid
    StudentEntity findByInstallmentsPaid(int installmentsPaid);

    // Find by overdue installments
    StudentEntity findByOverdueInstallments(int overdueInstallments);

    // Find by last payment date
    StudentEntity findByLastPaymentDate(LocalDate lastPaymentDate);

    // Find by total amount to pay
    StudentEntity findByTotalAmountToPay(int totalAmountToPay);

    // Find by total amount paid
    StudentEntity findByTotalAmountPaid(int totalAmountPaid);

}
