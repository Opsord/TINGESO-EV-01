package TopEducation.TopEducationApp;

import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import TopEducation.TopEducationApp.services.AdministrationOffice;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdministrationOfficeTest {
    StudentEntity student = new StudentEntity();

    StudentScoreEntity studentScore = new StudentScoreEntity();
    AdministrationOffice administrationOffice = new AdministrationOffice();

    @Test
        // Test for isValidStudent with correct values
    void isValidStudentCorrect() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2010);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(1);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the validation
        boolean isValid = administrationOffice.isValidStudent(student);
        assertTrue(isValid);
    }

    @Test
        // Test for isValidStudent with incorrect values
    void isValidStudentIncorrect() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(7);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2040);
        student.setExamsTaken(2);
        student.setAverageGrade(1100);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(1);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the validation
        boolean isValid = administrationOffice.isValidStudent(student);
        assertFalse(isValid);
    }

    @Test
        // Test for isValidStudentScore with correct values
    void isValidStudentScoreIncorrect() {
        // Setting up the student
        studentScore.setGradeRUT("20.000.000-2");
        studentScore.setScore(800);
        studentScore.setExamDate(LocalDate.of(2023, 4, 15));
        studentScore.setStudentName("Aquiles");
        studentScore.setStudentLastName("Baeza");

        // Simulating the validation
        boolean isValid = administrationOffice.isValidStudentScore(studentScore);
        assertTrue(isValid);
    }

    @Test
        // Test for isValidStudentScore with incorrect values
    void isValidStudentScoreCorrect() {
        // Setting up the student
        studentScore.setGradeRUT("20.000.000-2");
        studentScore.setScore(1100);
        studentScore.setExamDate(LocalDate.of(2024, 4, 15));
        studentScore.setStudentName("Aquiles");
        studentScore.setStudentLastName("Baeza");

        // Simulating the validation
        boolean isValid = administrationOffice.isValidStudentScore(studentScore);
        assertFalse(isValid);
    }

    @Test
        // Test for calculateMaxInstallments with correct values
    void calculateMaxInstallmentsCorrect() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setGraduationYear(2010);

        // Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(7, maxInstallments, 0.0);
    }

    @Test
        // Test for calculateMaxInstallments with incorrect values
    void calculateMaxInstallmentsInvalidGraduationYear() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(1600);

        // Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(0, maxInstallments, 0.0);
    }

    @Test
        // Test for calculateMaxInstallments with incorrect values
    void calculateMaxInstallmentsEmptyRut() {
        // Setting up the student
        student.setRut("");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1995, 10, 15));
        student.setSchoolType(2);
        student.setGraduationYear(2015);

        // Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(0, maxInstallments, 0.0);
    }

    @Test
        // Test for calculateMaxInstallments with an invalid school type
    void calculateMaxInstallmentsInvalidSchoolType() {
        // Setting up the student
        student.setRut("20.777.666-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1970, 1, 1));
        student.setSchoolType(10);
        student.setGraduationYear(2010);

        // Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(0, maxInstallments, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with correct values
    void calculateAnnualCostCash() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(785000, annualCostCash, 0.0);// 785.000 CLP
    }

    @Test
        // Test for calculateAnnualCostCash with incorrect RUT
    void calculateAnnualCostCashEmptyRut() {
        // Setting up the student
        student.setRut("");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1995, 10, 15));
        student.setSchoolType(0);
        student.setGraduationYear(2015);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with empty first name
    void calculateAnnualCostCashEmptyFirstName() {
        // Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1970, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2020);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with empty last name
    void calculateAnnualCostCashEmptyLastName() {
        // Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("Raul");
        student.setLastName("");
        student.setBirthDate(LocalDate.of(1999, 1, 20));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with invalid birthdate
    void calculateAnnualCostCashInvalidBirthDate() {
        // Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(2070, 10, 15));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with an invalid school type
    void calculateAnnualCostCashInvalidSchoolType() {
        // Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1970, 1, 1));
        student.setSchoolType(4);
        student.setGraduationYear(2010);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with invalid graduation year
    void calculateAnnualCostCashInvalidGraduationYear() {
        // Setting up the student
        student.setRut("20.000.666-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1970, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(1600);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateSchoolTypeDiscount with type 0
    void calculateSchoolTypeDiscountType0() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        // Simulating the payment in installments
        double schoolTypeDiscount = administrationOffice.calculateSchoolTypeDiscount(student);
        assertEquals(0.2, schoolTypeDiscount, 0.0);
    }

    @Test
        // Test for calculateSchoolTypeDiscount with type 1
    void calculateSchoolTypeDiscountType1() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setGraduationYear(2021);

        // Simulating the payment in installments
        double schoolTypeDiscount = administrationOffice.calculateSchoolTypeDiscount(student);
        assertEquals(0.1, schoolTypeDiscount, 0.0);
    }

    @Test
        // Test for calculateSchoolTypeDiscount with type 2
    void calculateSchoolTypeDiscountType2() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(2);
        student.setGraduationYear(2023);

        // Simulating the payment in installments
        double schoolTypeDiscount = administrationOffice.calculateSchoolTypeDiscount(student);
        assertEquals(0, schoolTypeDiscount, 0.0);
    }

    @Test
        // Test for calculateTimeAfterGraduationDiscount with correct values
    void calculateTimeAfterGraduationDiscountCorrectRange1() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2023);

        // Simulating the payment in installments
        double timeAfterGraduationDiscount = administrationOffice.calculateTimeAfterGraduationDiscount(student);
        assertEquals(0.15, timeAfterGraduationDiscount, 0.0);
    }

    @Test
        // Test for calculateTimeAfterGraduationDiscount with correct values
    void calculateTimeAfterGraduationDiscountCorrectRange2() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(2);
        student.setGraduationYear(2021);

        // Simulating the payment in installments
        double timeAfterGraduationDiscount = administrationOffice.calculateTimeAfterGraduationDiscount(student);
        assertEquals(0.08, timeAfterGraduationDiscount, 0.0);
    }

    @Test
        // Test for calculateTimeAfterGraduationDiscount with correct values
    void calculateTimeAfterGraduationDiscountCorrectRange3() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setGraduationYear(2019);

        // Simulating the payment in installments
        double timeAfterGraduationDiscount = administrationOffice.calculateTimeAfterGraduationDiscount(student);
        assertEquals(0.04, timeAfterGraduationDiscount, 0.0);
    }

    @Test
        // Test for calculateTimeAfterGraduationDiscount with correct values
    void calculateTimeAfterGraduationDiscountCorrectRange4() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Voy");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        // Simulating the payment in installments
        double timeAfterGraduationDiscount = administrationOffice.calculateTimeAfterGraduationDiscount(student);
        assertEquals(0, timeAfterGraduationDiscount, 0.0);
    }

    @Test
        // Test for calculateAverageScoreDiscount with correct values
    void calculateAverageScoreDiscountCorrectRange1() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2023);
        student.setAverageGrade(960);

        // Simulating the payment in installments
        double averageScoreDiscount = administrationOffice.calculateAverageScoreDiscount(student);
        assertEquals(0.1, averageScoreDiscount, 0.0);
    }

    @Test
        // Test for calculateAverageScoreDiscount with correct values
    void calculateAverageScoreDiscountCorrectRange2() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(2);
        student.setGraduationYear(2021);
        student.setAverageGrade(910);

        // Simulating the payment in installments
        double averageScoreDiscount = administrationOffice.calculateAverageScoreDiscount(student);
        assertEquals(0.05, averageScoreDiscount, 0.0);
    }

    @Test
        // Test for calculateAverageScoreDiscount with correct values
    void calculateAverageScoreDiscountCorrectRange3() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setGraduationYear(2019);
        student.setAverageGrade(860);

        // Simulating the payment in installments
        double averageScoreDiscount = administrationOffice.calculateAverageScoreDiscount(student);
        assertEquals(0.02, averageScoreDiscount, 0.0);
    }

    @Test
        // Test for calculateAverageScoreDiscount with correct values
    void calculateAverageScoreDiscountCorrectRange4() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Voy");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2010);
        student.setAverageGrade(800);

        // Simulating the payment in installments
        double averageScoreDiscount = administrationOffice.calculateAverageScoreDiscount(student);
        assertEquals(0, averageScoreDiscount, 0.0);
    }

    @Test
        // Test for calculateAnnualCostInstallments with type 2 and range 1
    void calculateAnnualCostInstallmentsType2Range1() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(2);
        student.setGraduationYear(2023);

        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1334500, annualCostInstallments, 1.0);// 1.334.500 CLP
    }

    @Test
        // Test for calculateAnnualCostInstallments with type 1 and range 2
    void calculateAnnualCostInstallmentsType1Range2() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setGraduationYear(2021);

        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1287400, annualCostInstallments, 1.0);// 1.287.400 CLP
    }

    @Test
        // Test for calculateAnnualCostInstallments with type 0 and range 3
    void calculateAnnualCostInstallmentsType0Range3() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2020);

        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1130400, annualCostInstallments, 1.0);
    }

    @Test
        // Test for calculateAnnualCostInstallments with type 0 and range 4
    void calculateAnnualCostInstallmentsType0Range4() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Voy");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setGraduationYear(2010);

        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1256000, annualCostInstallments, 1.0);// 1.256.000 CLP
    }

    @Test
        // Test for calculateAnnualCostInstallments with incorrect values
    void calculateAnnualCostInstallmentsEmpty() {
        // Setting up the student
        student.setRut("");
        student.setFirstName("");
        student.setLastName("");
        student.setBirthDate(LocalDate.of(1995, 6, 10));
        student.setSchoolType(1);
        student.setGraduationYear(2020);

        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(0, annualCostInstallments, 0.0);
    }

    @Test
        // Test for calculateFinalCost with correct values
    void calculateFinalCostCorrectCash() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2010);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(1);

        // Simulating the payment in installments
        double finalCost = administrationOffice.calculateFinalCost(student);
        assertEquals(785000, finalCost, 0.0);// 785.000 CLP
    }

    @Test
        // Test for calculateFinalCost with correct values
    void calculateFinalCostCorrectInstallments() {
        // Setting up the student
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2010);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Installments");
        student.setAgreedInstallments(3);

        // Simulating the payment in installments
        double finalCost = administrationOffice.calculateFinalCost(student);
        assertEquals(1256000, finalCost, 0.0);// 1.256.000 CLP
    }
}
