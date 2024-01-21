package TopEducation.TopEducationApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor

// Part of the persistence layer
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    // RUT is a unique identifier for Chilean citizens
    private String rut;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    // School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
    private int schoolType;

    private String schoolName;

    private int graduationYear;

    private int examsTaken;

    private int averageGrade;

    private String paymentMethod;

    private int agreedInstallments;

    private int installmentsPaid;

    private int overdueInstallments;

    private LocalDate lastPaymentDate;

    private int totalAmountToPay;

    private int totalAmountPaid;
}
