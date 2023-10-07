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

    // Constants

    // Enrollment cost -> 70.000 CLP
    private final int enrollmentCost = 70000;
    // Annual duty -> 1.500.000 CLP
    private final int annualDuty = 1500000;

    // Verifications

    // Verify if the student is valid
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

    // Verify if the student score is valid
    public boolean isValidStudentGrade(StudentScoreEntity studentScore) {
        // Verify if the student´s parameters are valid

        // Verify if the student has a valid rut
        if (studentScore.getGradeRUT() == null
                || studentScore.getGradeRUT().isBlank()) {
            return false;
        }
        // Verify if the student has a valid score
        if (studentScore.getScore() < 0
                || studentScore.getScore() > 1000) {
            return false;
        }
        // Verify if the exam date is from this year
        LocalDate currentDate = LocalDate.now();
        if (studentScore.getExamDate() == null
                || (studentScore.getExamDate().isAfter(currentDate))) {
            return false;
        }
        // Verify if the student name is valid
        if (studentScore.getStudentName() == null
                || studentScore.getStudentName().isBlank()) {
            return false;
        }
        // Verify if the student lastname is valid
        if (studentScore.getStudentLastName() == null
                || studentScore.getStudentLastName().isBlank()) {
            return false;
        }
        return true;
    }

    // Pre-enrollment calculations

    // Calculate the maximum number of installments
    public int calculateMaxInstallments(StudentEntity student) {
        // Getting the school type of the student
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
        // Adding a validation to avoid trouble
        if (isValidStudent(student)) {
            return maxInstallments;
        } else {
            return 0;
        }
    }

    // Calculate the annual cost if the payment is made in cash
    public double calculateAnnualCostCash(StudentEntity student) {
        double finalPrice = (enrollmentCost + annualDuty) * 0.5;
        // Adding a validation to avoid trouble
        if (isValidStudent(student)) {
            return finalPrice;
        } else {
            return 0;
        }
    }

    // Discount calculations

    // Calculate the discount depending on the type of school
    public double calculateSchoolTypeDiscount(StudentEntity student) {
        if (student.getSchoolType() == 0) {
            return 0.2;
        } else if (student.getSchoolType() == 1) {
            return 0.1;
        } else if (student.getSchoolType() == 2) {
            return 0;
        } else {
            return 0;
        }
    }

    // Calculate the discount depending on the years since graduation
    public double calculateTimeAfterGraduationDiscount(StudentEntity student) {
        // Getting the current date
        LocalDate currentYear = LocalDate.now();
        // Calculating how many years have passed since the student graduated
        int yearsSinceGraduation = currentYear.getYear() - student.getGraduationYear();
        // First range: 0-1 year
        if (yearsSinceGraduation < 1) {
            return 0.15;
            // Second range: 1-2 years
        } else if (yearsSinceGraduation < 3) {
            return 0.08;
            // Third range: 3-4 years
        } else if (yearsSinceGraduation < 5) {
            return 0.04;
            // Fourth range: 5+ years
        } else {
            return 0;
        }
    }

    // Calculate the discount depending on the average score
    public double calculateAverageScoreDiscount(StudentEntity student) {
        // Getting the average score of the student
        int averageScore = student.getAverageGrade();
        // First range: 950 – 1000
        if (averageScore >= 950) {
            return 0.1;
            // Second range: 900 – 949
        } else if (averageScore >= 900) {
            return 0.05;
            // Third range: 850 – 899
        } else if (averageScore >= 850) {
            return 0.02;
            // Fourth range: < 850
        } else {
            return 0;
        }
    }

    // Calculate the annual cost if the payment is made in installments
    public double calculateAnnualCostInstallments(StudentEntity student) {
        // Getting the annual cost
        int annualCost = enrollmentCost + annualDuty;
        // Getting the discounts
        double schoolTypeDis = calculateSchoolTypeDiscount(student);
        double timeAfterGraduationDis = calculateTimeAfterGraduationDiscount(student);
        double averageScoreDis = calculateAverageScoreDiscount(student);
        // Calculating the final price
        double finalPrice = annualCost * (1 - schoolTypeDis - timeAfterGraduationDis - averageScoreDis);
        // Adding a validation to avoid trouble
        if (isValidStudent(student)) {
            return finalPrice;
        } else {
            return 0;
        }
    }

    // Calculate the total amount to pay depending on if the payment is made in cash or installments
    public int calculateFinalCost(StudentEntity student) {
        if (student.getAgreedInstallments() == 1) {
            return (int) calculateAnnualCostCash(student);
        } else {
            return (int) calculateAnnualCostInstallments(student);
        }
    }

    // Check for overdue installments
    public void calculateGeneralInterest(StudentEntity student) {
        // Get the overdue installments
        ArrayList<InstallmentEntity> overdueInstallments = installmentService.findAllOverdueInstallmentsByRUT(student.getRut());
        // if there are overdue installments, calculate the interest and update the installment amount
        int overdueInstallmentsSize = overdueInstallments.size();
        if (!overdueInstallments.isEmpty()) {
            // Calculate the interest
            double interest = switch (overdueInstallmentsSize) {
                case 1 -> 0.03;
                case 2 -> 0.06;
                case 3 -> 0.09;
                default -> 0.12;
            };

            // Update every installment overdue price
            ArrayList<InstallmentEntity> installments = installmentService.findAllByInstallmentRUT(student.getRut());
            for (InstallmentEntity installment : installments) {
                int installmentOverduePrice = (int) (installment.getInstallmentAmount() * interest);
                installment.setInstallmentOverduePrice(installment.getInstallmentAmount() + installmentOverduePrice);
            }
        }
    }

    // Update student numbers
    public void updateStudentNumbers(StudentEntity student) {
        // Get an array of the installments that match the RUT of the student
        ArrayList<InstallmentEntity> installments = installmentService.findAllByInstallmentRUT(student.getRut());
        // Calculate the total amount paid and paid installments
        int totalAmountPaid = 0;
        int paidInstallments = 0;
        for (InstallmentEntity installment : installments) {
            if (installment.getInstallmentStatus() == 1) {
                totalAmountPaid += installment.getInstallmentAmount();
                paidInstallments++;
            }
        }
        // Update the total amount paid and paid installments
        student.setTotalAmountPaid(totalAmountPaid);
        student.setInstallmentsPaid(paidInstallments);

        // Calculate the total amount to pay if there are overdue installments
        ArrayList<InstallmentEntity> overdueInstallments = installmentService.findAllOverdueInstallmentsByRUT(student.getRut());
        int totalAmountToPay = 0;
        if (!overdueInstallments.isEmpty()) {
            for (InstallmentEntity installment : overdueInstallments) {
                totalAmountToPay += installment.getInstallmentOverduePrice();
            }
        } else {
            for (InstallmentEntity installment : installments) {
                totalAmountToPay += installment.getInstallmentAmount();
            }
        }
        // Update the total amount to pay
        student.setTotalAmountToPay(totalAmountToPay);
        // Update overdue installments
        student.setOverdueInstallments(overdueInstallments.size());

        // Update payment method (more efficient if it is done here)
        if (student.getAgreedInstallments() == 1) {
            student.setPaymentMethod("Cash");
        } else {
            student.setPaymentMethod("Installments");
        }
    }

    // Update last payment date
    public void updateLastPaymentDate(StudentEntity student) {
        // Get the paid installments of the student
        ArrayList<InstallmentEntity> paidInstallments = installmentService.findAllPaidInstallmentsByRUT(student.getRut());
        // Get the payment date of the closest installment to the current date
        LocalDate closestPaymentDate = LocalDate.of(LocalDate.now().getYear(), 1, 5);
        for (InstallmentEntity installment : paidInstallments) {
            if (installment.getInstallmentPaymentDate().isAfter(closestPaymentDate)) {
                closestPaymentDate = installment.getInstallmentPaymentDate();
            }
        }
        // Update the last payment date
        if (!paidInstallments.isEmpty()) {
            student.setLastPaymentDate(closestPaymentDate);
        } else {
            student.setLastPaymentDate(null);
        }
    }


    // Enrollment

    // Check if a student has missing installments
    public void checkMissingInstallments(StudentEntity student) {
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
        // Calculate the number of installments that are missing
        int missingInstallments = agreedInstallments - installments.size();

        // Verify if there are missing installments
        if (missingInstallments > 0) {
            // Generate the missing installments
            for (int i = 0; i < missingInstallments; i++) {
                // Create a new installment
                InstallmentEntity installment = new InstallmentEntity();
                installment.setInstallmentRUT(student.getRut());
                // Set the amount of the installment
                installment.setInstallmentAmount (calculateFinalCost(student) / agreedInstallments);
                // Set the payment date of the installment (starting from the 5th on january)
                LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 5);
                installment.setInstallmentPaymentDate(startDate.plusMonths(i));
                // Set the status - Installment status: 0 -> Pending, 1 -> Paid
                installment.setInstallmentStatus(0);
                installmentService.updateInstallmentOverdueStatus(installment);
                // Set the overdue price
                installment.setInstallmentOverduePrice(0);

                // Save the installment
                installmentService.saveInstallment(installment);
            }
        }
    }

    // Update student installments
        public void updateStudent(StudentEntity student) {
        // Check if there are missing installments
        checkMissingInstallments(student);
        // Apply the interest if needed
        calculateGeneralInterest(student);
        // Update the total amount paid
        updateStudentNumbers(student);
        // Update the last payment date
        updateLastPaymentDate(student);
    }

}
