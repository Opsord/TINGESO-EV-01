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
    //Test for the calculateAnnualCostCash with correct values
    void calculateAnnualCostCash() {
        //Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        //Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(785000, annualCostCash, 0.0);//785.000 CLP
    }

    @Test
        //Test for the calculateAnnualCostCash with incorrect RUT
    void calculateAnnualCostCashEmptyRut() {
        //Setting up the student
        student.setRut("");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1995, 10, 15));
        student.setSchoolType(0);
        student.setGraduationYear(2015);

        //Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
    //Test for the calculateAnnualCostCash with empty first name
    void calculateAnnualCostCashEmptyFirstName() {
        //Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1970, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2020);

        //Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
    //Test for the calculateAnnualCostCash with empty last name
    void calculateAnnualCostCashEmptyLastName() {
        //Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("Raul");
        student.setLastName("");
        student.setBirthDate(LocalDate.of(1999, 1, 20));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        //Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
    //Test for the calculateAnnualCostCash with invalid birthdate
    void calculateAnnualCostCashInvalidBirthDate() {
        //Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(2070, 10, 15));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        //Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
    //Test for the calculateAnnualCostCash with invalid school type
    void calculateAnnualCostCashInvalidSchoolType() {
        //Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1970, 1, 1));
        student.setSchoolType(4);
        student.setGraduationYear(2010);

        //Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
    //Test for the calculateAnnualCostCash with invalid graduation year
    void calculateAnnualCostCashInvalidGraduationYear() {
        //Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1970, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(1600);

        //Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
    //Test for the calculateAnnualCostInstallments with type 2 and range 1
    void calculateAnnualCostInstallmentsType2Range1() {
        //Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Casas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(2);
        student.setGraduationYear(2023);

        //Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1334500, annualCostInstallments, 1.0);//1.334.500 CLP
    }

    @Test
        //Test for the calculateAnnualCostInstallments with type 1 and range 2
    void calculateAnnualCostInstallmentsType1Range2() {
        //Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Casas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setGraduationYear(2021);

        //Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1287400, annualCostInstallments, 1.0);//1.287.400 CLP
    }

    @Test
        //Test for the calculateAnnualCostInstallments with type 0 and range 3
    void calculateAnnualCostInstallmentsType0Range3() {
        //Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2020);

        //Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1193200, annualCostInstallments, 1.0);//1.193.200 CLP
    }

    //Test for the calculateAnnualCostInstallments with type 0 and range 4
    @Test
    void calculateAnnualCostInstallmentsType0Range4() {
        //Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Voy");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        //Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1256000, annualCostInstallments, 1.0);//1.256.000 CLP
    }

    @Test
    //Test for the calculateAnnualCostInstallments with incorrect values
    void calculateAnnualCostInstallmentsEmpty() {
        //Setting up the student
        student.setRut("");
        student.setFirstName("");
        student.setLastName("");
        student.setBirthDate(LocalDate.of(1995, 6, 10));
        student.setSchoolType(1);
        student.setGraduationYear(2020);

        //Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(0, annualCostInstallments, 0.0);
    }

    @Test
    //Test for the calculate max installments with correct values
    void calculateMaxInstallments() {
        //Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setGraduationYear(2010);

        //Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(7, maxInstallments, 0.0);
    }

    //Test for the calculate max installments with incorrect values
    @Test
    void calculateMaxInstallmentsInvalidGraduationYear() {
        //Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(1600);

        //Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(0, maxInstallments, 0.0);
    }

    @Test
    //Test for the calculate max installments with incorrect values
    void calculateMaxInstallmentsEmptyRut() {
        //Setting up the student
        student.setRut("");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1995, 10, 15));
        student.setSchoolType(2);
        student.setGraduationYear(2015);

        //Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(0, maxInstallments, 0.0);
    }

    @Test
    //Test for the calculate max installments with invalid school type
    void calculateMaxInstallmentsInvalidSchoolType() {
        //Setting up the student
        student.setRut("20.777.666-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1970, 1, 1));
        student.setSchoolType(10);
        student.setGraduationYear(2010);

        //Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(0, maxInstallments, 0.0);
    }

}
