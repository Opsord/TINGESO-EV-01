package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.StudentEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service

//Part of the business layer
public class AdministrationOffice {

    //Verify is a student is valid
    public boolean isValidStudent(StudentEntity student) {
        //Verify if the student´s parameters are valid

        //Verify if the student has a valid rut
        if (student.getRut() == null || student.getRut().isBlank()) {
            return false;
        }
        //Verify if the student has a valid first name
        if (student.getFirstName() == null || student.getFirstName().isBlank() ) {
            return false;
        }
        //Verify if the student  last name
        if (student.getLastName() == null || student.getLastName().isBlank()) {
            return false;
        }
        //Verify if the student´s birthdate is valid
        LocalDate currentDate = LocalDate.now();
        if (student.getBirthDate() == null || student.getBirthDate().isAfter(currentDate)) {
            return false;
        }
        //Verify if the student has a valid school type
        if (!(student.getSchoolType() == 0 || student.getSchoolType() == 1 || student.getSchoolType() == 2)) {
            return false;
        }
        //Verify if the student has a valid graduation year
        int currentYear = java.time.LocalDate.now().getYear();
        if (student.getGraduationYear() < 1900 || student.getGraduationYear() > currentYear) {
            return false;
        }
        return true;
    }

    //Enrollment cost -> 70.000 CLP
    private final int enrollmentCost = 70000;
    //Annual duty     -> 1.500.000 CLP
    private final int annualDuty = 1500000;
    //Total cost      -> 1.570.000 CLP

    //Calculate the annual cost if the payment is made in cash
    public double calculateAnnualCostCash(StudentEntity student) {
        double finalPrice = (enrollmentCost + annualDuty) * 0.5;
        //Adding a validation to avoid fraud
        if (isValidStudent(student)) {
            return finalPrice;
        } else {
            return 0;
        }
    }

    //Calculate the annual cost if the payment is made in installments
    public double calculateAnnualCostInstallments(StudentEntity student) {
        //Calculate discount depending on the type of school
        double schoolTypeDiscount = 0;
        //School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
        if (student.getSchoolType() == 0) {
            schoolTypeDiscount = 0.2;
        } else if (student.getSchoolType() == 1) {
            schoolTypeDiscount = 0.1;
        } else if (student.getSchoolType() == 2) {
            schoolTypeDiscount = 0;
        }

        //Calculate discount depending on the years since graduation
        double yearsSinceGraduationDiscount;
        //Getting the current date
        LocalDate currentYear = LocalDate.now();
        //Calculating how many years have passed since the student graduated
        int yearsSinceGraduation = currentYear.getYear() - student.getGraduationYear();
        //First range: 0 - 1 year
        if (yearsSinceGraduation < 1) {
            yearsSinceGraduationDiscount = 0.15;
        //Second range: 1 - 2 years
        } else if (yearsSinceGraduation < 3) {
            yearsSinceGraduationDiscount = 0.08;
        //Third range: 3 - 4 years
        } else if (yearsSinceGraduation < 5) {
            yearsSinceGraduationDiscount = 0.04;
        //Fourth range: 5+ years
        } else {
            yearsSinceGraduationDiscount = 0;
        }

        //Calculate the total discount
        double totalDiscount = schoolTypeDiscount + yearsSinceGraduationDiscount;
        //Calculate the total cost
        double finalPrice = ((enrollmentCost + annualDuty) * (1 - totalDiscount));

        //Adding a validation to avoid fraud
        if (isValidStudent(student)) {
            return finalPrice;
        } else {
            return 0;
        }
    }

}
