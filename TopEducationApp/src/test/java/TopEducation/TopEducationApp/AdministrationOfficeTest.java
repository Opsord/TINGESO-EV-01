package TopEducation.TopEducationApp;

import TopEducation.TopEducationApp.entities.InstallmentEntity;
import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import TopEducation.TopEducationApp.services.AdministrationOffice;
import TopEducation.TopEducationApp.services.InstallmentService;
import TopEducation.TopEducationApp.services.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdministrationOfficeTest {

    @Autowired
    AdministrationOffice administrationOffice;

    @Autowired
    InstallmentService installmentService;

    @Autowired
    StudentService studentService;


    @Test
        // Test for isValidStudent with correct values
    void isValidStudentCorrect() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
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
    void isValidStudentIncorrectSchoolType() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
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
        // Test for isValidStudent with incorrect values
    void isValidStudentIncorrectRUT() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("  ");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
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
    void isValidStudentScoreCorrect() {
        // Setting up the student
        StudentScoreEntity studentScore = new StudentScoreEntity();
        studentScore.setScoreRUT("20.000.000-2");
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
    void isValidStudentScoreIncorrectRUT() {
        // Setting up the student
        StudentScoreEntity studentScore = new StudentScoreEntity();
        studentScore.setScoreRUT(" ");
        studentScore.setScore(800);
        studentScore.setExamDate(LocalDate.of(2024, 4, 15));
        studentScore.setStudentName("Aquiles");
        studentScore.setStudentLastName("Baeza");

        // Simulating the validation
        boolean isValid = administrationOffice.isValidStudentScore(studentScore);
        assertFalse(isValid);
    }

    @Test
        // Test for isValidStudentScore with incorrect values
    void isValidStudentScoreIncorrectScore() {
        // Setting up the student
        StudentScoreEntity studentScore = new StudentScoreEntity();
        studentScore.setScoreRUT("20.000.000-2");
        studentScore.setScore(1100);
        studentScore.setExamDate(LocalDate.of(2024, 4, 15));
        studentScore.setStudentName("Aquiles");
        studentScore.setStudentLastName("Baeza");

        // Simulating the validation
        boolean isValid = administrationOffice.isValidStudentScore(studentScore);
        assertFalse(isValid);
    }

    @Test
        // Test for isValidStudentScore with incorrect values
    void isValidStudentScoreIncorrectExamDate() {
        // Setting up the student
        StudentScoreEntity studentScore = new StudentScoreEntity();
        studentScore.setScoreRUT("20.000.000-2");
        studentScore.setScore(800);
        studentScore.setExamDate(LocalDate.of(2090, 4, 15));
        studentScore.setStudentName("Aquiles");
        studentScore.setStudentLastName("Baeza");

        // Simulating the validation
        boolean isValid = administrationOffice.isValidStudentScore(studentScore);
        assertFalse(isValid);
    }

    @Test
        // Test for isValidStudentScore with incorrect values
    void isValidStudentScoreIncorrectStudentName() {
        // Setting up the student
        StudentScoreEntity studentScore = new StudentScoreEntity();
        studentScore.setScoreRUT("20.000.000-2");
        studentScore.setScore(800);
        studentScore.setExamDate(LocalDate.of(2024, 4, 15));
        studentScore.setStudentName("");
        studentScore.setStudentLastName("Baeza");

        // Simulating the validation
        boolean isValid = administrationOffice.isValidStudentScore(studentScore);
        assertFalse(isValid);
    }

    @Test
        // Test for isValidStudentScore with incorrect values
    void isValidStudentScoreIncorrectStudentLastName() {
        // Setting up the student
        StudentScoreEntity studentScore = new StudentScoreEntity();
        studentScore.setScoreRUT("20.000.000-2");
        studentScore.setScore(800);
        studentScore.setExamDate(LocalDate.of(2024, 4, 15));
        studentScore.setStudentName("Aquiles");
        studentScore.setStudentLastName(" ");

        // Simulating the validation
        boolean isValid = administrationOffice.isValidStudentScore(studentScore);
        assertFalse(isValid);
    }

    @Test
        // Test for calculateMaxInstallments with correct values
    void calculateMaxInstallmentsCorrect() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
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
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(7);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2040);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(1);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(0, maxInstallments, 0.0);
    }

    @Test
        // Test for calculateMaxInstallments with incorrect values
    void calculateMaxInstallmentsEmptyRut() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut(" ");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(2000, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(0, maxInstallments, 0.0);
    }

    @Test
        // Test for calculateMaxInstallments with an invalid school type
    void calculateMaxInstallmentsInvalidSchoolType() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(2000, 1, 1));
        student.setSchoolType(7);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        int maxInstallments = administrationOffice.calculateMaxInstallments(student);
        assertEquals(0, maxInstallments, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with correct values
    void calculateAnnualCostCash() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(2000, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(785000, annualCostCash, 0.0);// 785.000 CLP
    }

    @Test
        // Test for calculateAnnualCostCash with incorrect RUT
    void calculateAnnualCostCashEmptyRut() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut(" ");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(2000, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with empty first name
    void calculateAnnualCostCashEmptyFirstName() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName(" ");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(2000, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with empty last name
    void calculateAnnualCostCashEmptyLastName() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName(" ");
        student.setBirthDate(LocalDate.of(2000, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with invalid birthdate
    void calculateAnnualCostCashInvalidBirthDate() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(2060, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with an invalid school type
    void calculateAnnualCostCashInvalidSchoolType() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(2000, 1, 1));
        student.setSchoolType(7);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateAnnualCostCash with invalid graduation year
    void calculateAnnualCostCashInvalidGraduationYear() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Baeza");
        student.setBirthDate(LocalDate.of(2000, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2070);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in cash
        double annualCostCash = administrationOffice.calculateAnnualCostCash(student);
        assertEquals(0, annualCostCash, 0.0);
    }

    @Test
        // Test for calculateSchoolTypeDiscount with type 0
    void calculateSchoolTypeDiscountType0() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2010);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double schoolTypeDiscount = administrationOffice.calculateSchoolTypeDiscount(student);
        assertEquals(0.2, schoolTypeDiscount, 0.0);
    }

    @Test
        // Test for calculateSchoolTypeDiscount with type 1
    void calculateSchoolTypeDiscountType1() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2021);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double schoolTypeDiscount = administrationOffice.calculateSchoolTypeDiscount(student);
        assertEquals(0.1, schoolTypeDiscount, 0.0);
    }

    @Test
        // Test for calculateSchoolTypeDiscount with type 2
    void calculateSchoolTypeDiscountType2() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(2);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2023);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double schoolTypeDiscount = administrationOffice.calculateSchoolTypeDiscount(student);
        assertEquals(0, schoolTypeDiscount, 0.0);
    }

    @Test
        // Test for calculateTimeAfterGraduationDiscount with correct values
    void calculateTimeAfterGraduationDiscountCorrectRange1() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2023);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double timeAfterGraduationDiscount = administrationOffice.calculateTimeAfterGraduationDiscount(student);
        assertEquals(0.15, timeAfterGraduationDiscount, 0.0);
    }

    @Test
        // Test for calculateTimeAfterGraduationDiscount with correct values
    void calculateTimeAfterGraduationDiscountCorrectRange2() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(2);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2021);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double timeAfterGraduationDiscount = administrationOffice.calculateTimeAfterGraduationDiscount(student);
        assertEquals(0.08, timeAfterGraduationDiscount, 0.0);
    }

    @Test
        // Test for calculateTimeAfterGraduationDiscount with correct values
    void calculateTimeAfterGraduationDiscountCorrectRange3() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2019);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double timeAfterGraduationDiscount = administrationOffice.calculateTimeAfterGraduationDiscount(student);
        assertEquals(0.04, timeAfterGraduationDiscount, 0.0);
    }

    @Test
        // Test for calculateTimeAfterGraduationDiscount with correct values
    void calculateTimeAfterGraduationDiscountCorrectRange4() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Voy");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2010);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double timeAfterGraduationDiscount = administrationOffice.calculateTimeAfterGraduationDiscount(student);
        assertEquals(0, timeAfterGraduationDiscount, 0.0);
    }

    @Test
        // Test for calculateAverageScoreDiscount with correct values
    void calculateAverageScoreDiscountCorrectRange1() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2023);
        student.setExamsTaken(2);
        student.setAverageGrade(960);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double averageScoreDiscount = administrationOffice.calculateAverageScoreDiscount(student);
        assertEquals(0.1, averageScoreDiscount, 0.0);
    }

    @Test
        // Test for calculateAverageScoreDiscount with correct values
    void calculateAverageScoreDiscountCorrectRange2() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(2);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2021);
        student.setExamsTaken(2);
        student.setAverageGrade(910);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double averageScoreDiscount = administrationOffice.calculateAverageScoreDiscount(student);
        assertEquals(0.05, averageScoreDiscount, 0.0);
    }

    @Test
        // Test for calculateAverageScoreDiscount with correct values
    void calculateAverageScoreDiscountCorrectRange3() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2019);
        student.setExamsTaken(2);
        student.setAverageGrade(860);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double averageScoreDiscount = administrationOffice.calculateAverageScoreDiscount(student);
        assertEquals(0.02, averageScoreDiscount, 0.0);
    }

    @Test
        // Test for calculateAverageScoreDiscount with correct values
    void calculateAverageScoreDiscountCorrectRange4() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Voy");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2010);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double averageScoreDiscount = administrationOffice.calculateAverageScoreDiscount(student);
        assertEquals(0, averageScoreDiscount, 0.0);
    }

    @Test
        // Test for calculateAnnualCostInstallments with type 2 and range 1
    void calculateAnnualCostInstallmentsType2Range1() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(2);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2023);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);


        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1334500, annualCostInstallments, 1.0);// 1.334.500 CLP
    }

    @Test
        // Test for calculateAnnualCostInstallments with type 1 and range 2
    void calculateAnnualCostInstallmentsType1Range2() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Armando");
        student.setLastName("Mesas");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(1);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2021);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1287400, annualCostInstallments, 1.0);// 1.287.400 CLP
    }

    @Test
        // Test for calculateAnnualCostInstallments with type 0 and range 3
    void calculateAnnualCostInstallmentsType0Range3() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Juan");
        student.setLastName("Perez");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2020);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1130400, annualCostInstallments, 1.0);
    }

    @Test
        // Test for calculateAnnualCostInstallments with type 0 and range 4
    void calculateAnnualCostInstallmentsType0Range4() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("20.000.000-2");
        student.setFirstName("Aquiles");
        student.setLastName("Voy");
        student.setBirthDate(LocalDate.of(1990, 1, 1));
        student.setSchoolType(0);
        student.setSchoolName("Colegio de Prueba");
        student.setGraduationYear(2010);
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(1256000, annualCostInstallments, 1.0);// 1.256.000 CLP
    }

    @Test
        // Test for calculateAnnualCostInstallments with incorrect values
    void calculateAnnualCostInstallmentsEmpty() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
        student.setRut("");
        student.setFirstName("");
        student.setLastName("");
        student.setBirthDate(LocalDate.of(1995, 6, 10));
        student.setSchoolType(1);
        student.setGraduationYear(2020);
        student.setSchoolName("Colegio de Prueba");
        student.setExamsTaken(2);
        student.setAverageGrade(800);
        student.setPaymentMethod("Cash");
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double annualCostInstallments = administrationOffice.calculateAnnualCostInstallments(student);
        assertEquals(0, annualCostInstallments, 0.0);
    }

    @Test
        // Test for calculateFinalCost with correct values
    void calculateFinalCostCorrectCash() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
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
        student.setTotalAmountPaid(785000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double finalCost = administrationOffice.calculateFinalCost(student);
        assertEquals(785000, finalCost, 0.0);// 785.000 CLP
    }

    @Test
        // Test for calculateFinalCost with correct values
    void calculateFinalCostCorrectInstallments() {
        // Setting up the student
        StudentEntity student = new StudentEntity();
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
        student.setTotalAmountPaid(1256000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        double finalCost = administrationOffice.calculateFinalCost(student);
        assertEquals(1256000, finalCost, 0.0);// 1.256.000 CLP
    }

    @Test
        // Test for calculateGeneralInterest with correct values
    void calculateGeneralInterestCorrect() {
        // Setting up the student
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

        // Simulating the payment in installments
        administrationOffice.checkMissingInstallments(student);
        // Getting the installments
        ArrayList<InstallmentEntity> studentInstallments = installmentService.findAllByInstallmentRUT(student.getRut());
        for (InstallmentEntity installment: studentInstallments) {
            System.out.println(installment);
        }

        // Calculating the general interest
        administrationOffice.calculateGeneralInterest(student);
        // Getting the installments with the interest
        ArrayList<InstallmentEntity> studentInstallmentsWithInterest = installmentService.findAllByInstallmentRUT(student.getRut());
        for (InstallmentEntity installment: studentInstallmentsWithInterest) {
            System.out.println(installment);
        }

        // Comparing if each installment has a corresponding installment with interest


        // Deleting the installments
        for (InstallmentEntity installment: studentInstallments) {
            installmentService.deleteInstallment(installment.getId());
        }
    }

    @Test
        // Test for updateStudentNumbers with correct values
    void updateStudentNumbersCorrect() {
        // Setting up the student
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
        student.setAgreedInstallments(5);
        student.setTotalAmountPaid(0);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        administrationOffice.checkMissingInstallments(student);

        // Updating the student numbers
        administrationOffice.updateStudentNumbers(student);

        // Checking the number of installments
        assertEquals(student.getAgreedInstallments(),student.getOverdueInstallments(),0.0);

        // Deleting the installments
        ArrayList<InstallmentEntity> studentInstallments = installmentService.findAllByInstallmentRUT(student.getRut());
        for (InstallmentEntity installment: studentInstallments) {
            installmentService.deleteInstallment(installment.getId());
        }

    }

    @Test
        // Test for updateLastPaymentDate with correct values
    void updateLastPaymentDateCorrect() {
        // Setting up the student
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
        student.setAgreedInstallments(1);
        student.setInstallmentsPaid(1);
        student.setTotalAmountPaid(1256000);
        student.setTotalAmountToPay(0);
        student.setLastPaymentDate(LocalDate.of(2021, 1, 1));

        // Simulating a installment
        InstallmentEntity installment = new InstallmentEntity();
        installment.setInstallmentRUT(student.getRut());
        installment.setInstallmentAmount(1256000);
        installment.setInstallmentStatus(1);
        installment.setInstallmentPaymentDate(LocalDate.of(2023, 2, 1));
        installment.setInstallmentOverdueStatus(0);
        installment.setInstallmentOverduePrice(0);

        installmentService.saveInstallment(installment);

        // Simulating the payment in installments
        administrationOffice.updateLastPaymentDate(student);

        assertEquals(student.getLastPaymentDate(), installment.getInstallmentPaymentDate());

        // Deleting the installments
        installmentService.deleteInstallment(installment.getId());
    }

    @Test
        // Test for checkMissingInstallments with correct values
    void checkMissingInstallmentsCorrect() {
        // Setting up the student
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
        student.setAgreedInstallments(3);
        student.setInstallmentsPaid(2);
        student.setTotalAmountPaid(1256000);
        student.setTotalAmountToPay(0);

        // Simulating the payment in installments
        administrationOffice.checkMissingInstallments(student);

        ArrayList<InstallmentEntity> studentInstallments = installmentService.findAllByInstallmentRUT(student.getRut());
        assertEquals(student.getAgreedInstallments(), studentInstallments.size(), 0.0);

        // Deleting the installments
        for (InstallmentEntity installment: studentInstallments) {
            installmentService.deleteInstallment(installment.getId());
        }
    }

}