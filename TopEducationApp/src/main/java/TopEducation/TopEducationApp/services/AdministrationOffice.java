package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.InstallmentEntity;
import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.entities.StudentScoreEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service

// Part of the business layer
public class AdministrationOffice {

    @Autowired
    private InstallmentService installmentService;

    // Enrollment cost -> 70.000 CLP
    private final int enrollmentCost = 70000;
    // Annual duty -> 1.500.000 CLP
    private final int annualDuty = 1500000;
    // Total cost -> 1.570.000 CLP

    // Verify is a student is valid
    public boolean isValidStudent(StudentEntity student) {
        // Verify if the student´s parameters are valid

        // Verify if the student has a valid rut
        if (student.getRut() == null
                || student.getRut().isBlank()) {
            return false;
        }
        // Verify if the student has a valid first name
        if (student.getFirstName() == null
                || student.getFirstName().isBlank()) {
            return false;
        }
        // Verify if the student last name
        if (student.getLastName() == null
                || student.getLastName().isBlank()) {
            return false;
        }
        // Verify if the student´s birthdate is valid
        LocalDate currentDate = LocalDate.now();
        int currentYear = java.time.LocalDate.now().getYear();
        if (student.getBirthDate() == null
                || student.getBirthDate().isAfter(currentDate)
                || (currentYear - student.getBirthDate().getYear() > 100)) {
            return false;
        }
        // Verify if the student has a valid school type
        if (!(student.getSchoolType() == 0
                || student.getSchoolType() == 1
                || student.getSchoolType() == 2)) {
            return false;
        }
        // Verify if the student has a valid graduation year

        if (student.getGraduationYear() < 1900
                || (student.getGraduationYear() > currentYear)) {
            return false;
        }
        return true;
    }

    public boolean isValidStudentGrade(StudentScoreEntity studentGrade) {
        // Verify if the student´s parameters are valid

        // Verify if the student has a valid rut
        if (studentGrade.getGradeRUT() == null
                || studentGrade.getGradeRUT().isBlank()) {
            return false;
        }
        // Verify if the student has a valid score
        if (studentGrade.getScore() < 0
                || studentGrade.getScore() > 1000) {
            return false;
        }
        // Verify if the exam date is from this year
        LocalDate currentDate = LocalDate.now();
        if (studentGrade.getExamDate() == null
                || (studentGrade.getExamDate().isAfter(currentDate))) {
            return false;
        }
        // Verify if the student name is valid
        if (studentGrade.getStudentName() == null
                || studentGrade.getStudentName().isBlank()) {
            return false;
        }
        // Verify if the student last name is valid
        if (studentGrade.getStudentLastName() == null
                || studentGrade.getStudentLastName().isBlank()) {
            return false;
        }
        return true;
    }

    // Calculate the maximum number of installments
    public int calculateMaxInstallments(StudentEntity student) {
        // Getting the type of school of the student
        int schoolType = student.getSchoolType();
        int maxInstallments = 0;
        // School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
        if (schoolType == 0) {
            maxInstallments = 10;
        } else if (schoolType == 1) {
            maxInstallments = 7;
        } else if (schoolType == 2) {
            maxInstallments = 4;
        }
        // Adding a validation to avoid fraud
        if (isValidStudent(student)) {
            return maxInstallments;
        } else {
            return 0;
        }
    }

    // Calculate the annual cost if the payment is made in cash
    public double calculateAnnualCostCash(StudentEntity student) {
        double finalPrice = (enrollmentCost + annualDuty) * 0.5;
        // Adding a validation to avoid fraud
        if (isValidStudent(student)) {
            return finalPrice;
        } else {
            return 0;
        }
    }

    // Calculate the annual cost if the payment is made in installments
    public double calculateAnnualCostInstallments(StudentEntity student) {
        // Calculate discount depending on the type of school
        double schoolTypeDiscount = 0;
        double averageScoreDiscount = 0;
        // School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
        if (student.getSchoolType() == 0) {
            schoolTypeDiscount = 0.2;
        } else if (student.getSchoolType() == 1) {
            schoolTypeDiscount = 0.1;
        } else if (student.getSchoolType() == 2) {
            schoolTypeDiscount = 0;
        }

        // Calculate discount depending on the years since graduation
        double yearsSinceGraduationDiscount;
        // Getting the current date
        LocalDate currentYear = LocalDate.now();
        // Calculating how many years have passed since the student graduated
        int yearsSinceGraduation = currentYear.getYear() - student.getGraduationYear();
        // First range: 0 - 1 year
        if (yearsSinceGraduation < 1) {
            yearsSinceGraduationDiscount = 0.15;
            // Second range: 1 - 2 years
        } else if (yearsSinceGraduation < 3) {
            yearsSinceGraduationDiscount = 0.08;
            // Third range: 3 - 4 years
        } else if (yearsSinceGraduation < 5) {
            yearsSinceGraduationDiscount = 0.04;
            // Fourth range: 5+ years
        } else {
            yearsSinceGraduationDiscount = 0;
        }

        // Calculate the total discount
        double totalDiscount = schoolTypeDiscount + yearsSinceGraduationDiscount;
        // Calculate the total cost
        double finalPrice = ((enrollmentCost + annualDuty) * (1 - totalDiscount));

        // Adding a validation to avoid fraud
        if (isValidStudent(student)) {
            return finalPrice;
        } else {
            return 0;
        }
    }

    // Update student installments
    public void updateStudentInstallments(StudentEntity student) {
        // Calculate the maximum number of installments
        int maxInstallments = calculateMaxInstallments(student);
        // Get the number of installments agreed by the student
        int agreedInstallments = student.getAgreedInstallments();
        // Verify if the number of installments agreed by the student is valid
        if (agreedInstallments > maxInstallments) {
            agreedInstallments = maxInstallments;
        }
        // Get an array of the installments that match the RUT of the student
        ArrayList<InstallmentEntity> installments = installmentService.findAllByInstallmentRUT(student.getRut());
        // Verify if the number of installments agreed by the student are generated
        int missingInstallments = maxInstallments - installments.size();
        if (missingInstallments > 0) {
            // Generate the missing installments
            for (int i = 0; i < missingInstallments; i++) {
                // Create a new installment
                InstallmentEntity installment = new InstallmentEntity();
                // Set the RUT of the student
                installment.setInstallmentRUT(student.getRut());
                // Set the amount of the installment
                installment.setInstallmentAmount((int) calculateAnnualCostInstallments(student) / maxInstallments);
                // Set the payment date of the installment
                installment.setInstallmentPaymentDate(LocalDate.now().plusMonths(i));
                // Set the status of the installment Installment status: 0 -> Pending, 1 -> Paid
                installment.setInstallmentStatus(0);
                // Save the installment
                installmentService.saveInstallment(installment);
            }
        }

    }
}
