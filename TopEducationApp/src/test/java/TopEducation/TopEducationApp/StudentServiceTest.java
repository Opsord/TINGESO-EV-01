package TopEducation.TopEducationApp;

import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Test
        // Test for saveStudent, deleteStudent, and findById methods
    void saveAndDeleteStudent() {
        StudentEntity student = new StudentEntity();
        student.setRut("99.999.999-K");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2010);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Installments");
        student.setAgreedInstallments(2);
        student.setTotalAmountPaid(0);
        student.setTotalAmountToPay(0);

        // Save the student
        studentService.saveStudent(student);

        // Verify that the student was saved
        StudentEntity savedStudent = studentService.findById(student.getId());

        assert (savedStudent.getRut().equals("99.999.999-K"));
        assert (savedStudent.getFirstName().equals("Aquiles"));
        assert (savedStudent.getLastName().equals("Baeza"));
        assert (savedStudent.getBirthDate().equals(LocalDate.of(1990, 1, 1)));
        assert (savedStudent.getSchoolType() == 0);
        assert (savedStudent.getSchoolName().equals("Colegio de Prueba"));
        assert (savedStudent.getGraduationYear() == 2010);
        assert (savedStudent.getExamsTaken() == 2);
        assert (savedStudent.getAverageGrade() == 800);
        assert (savedStudent.getPaymentMethod().equals("Installments"));
        assert (savedStudent.getAgreedInstallments() == 2);
        assert (savedStudent.getTotalAmountPaid() == 0);
        assert (savedStudent.getTotalAmountToPay() == 0);

        // Delete the student
        studentService.deleteStudent(student.getId());

        // Verify that the student was deleted
        StudentEntity deletedStudent = studentService.findById(student.getId());
        assert (deletedStudent == null);

    }

}
