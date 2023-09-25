package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.StudentEntity;
import org.springframework.stereotype.Service;

@Service

//Part of the business layer
public class AdministrationOffice {

    //Enrollment cost -> 70.000 CLP
    private final int enrollmentCost = 70000;
    //Annual duty     -> 1.500.000 CLP
    private final int annualDuty = 1500000;
    //Total cost      -> 1.570.000 CLP

    //Calculate annual cost considering the enrollment cost and the annual duty
    //If the payment is made in cash, it has a 50% discount
    //If the payment is made in installments, it has a discount depending on
    //the type of school and the years since graduation

    //Calculate the annual cost if the payment is made in cash
    public int calculateAnnualCostCash(StudentEntity student) {
        return (int) ((enrollmentCost + annualDuty) * 0.5);
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
        //Getting the current year
        int currentYear = java.time.LocalDate.now().getYear();
        int yearsSinceGraduation =  currentYear - student.getGraduationYear();
        if (yearsSinceGraduation < 1) {
            yearsSinceGraduationDiscount = 0.15;
        } else if (yearsSinceGraduation < 3) {
            yearsSinceGraduationDiscount = 0.08;
        } else if (yearsSinceGraduation < 5) {
            yearsSinceGraduationDiscount = 0.04;
        } else {
            yearsSinceGraduationDiscount = 0;
        }

        //Calculate the total discount
        double totalDiscount = schoolTypeDiscount + yearsSinceGraduationDiscount;
        //Calculate the total cost
        return (enrollmentCost + annualDuty - ((enrollmentCost + annualDuty) * totalDiscount));
    }
}
