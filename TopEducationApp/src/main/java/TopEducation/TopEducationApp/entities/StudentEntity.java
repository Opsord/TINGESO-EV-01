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

    // First name
    private String firstName;

    // Last name
    private String lastName;

    // Birthdate
    private LocalDate birthDate;

    // School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
    private int schoolType;

    // School name
    private String schoolName;

    // Year of graduation
    private int graduationYear;

    // Average grade in tests
    private double averageGrade;

}
