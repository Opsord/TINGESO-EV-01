package TopEducation.TopEducationApp;

import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.services.AdministrationOffice;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AdministrationOfficeTest {
    StudentEntity student = new StudentEntity();
    AdministrationOffice administrationOffice = new AdministrationOffice();

    @Test
    void calculateAnnualCostCash() {
        //Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        //Simulating the payment in cash
        int annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(785000, annualCostCash, 0.0);  //785.000 CLP
    }
}
