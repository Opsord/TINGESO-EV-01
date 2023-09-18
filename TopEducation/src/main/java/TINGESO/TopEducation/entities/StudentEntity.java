package TINGESO.TopEducation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    //RUT is a unique identifier for Chilean citizens
    private long rut;

    //First name
    private String firstName;

    //Last name
    private String lastName;

    //Birthdate
    private LocalDate birthDate;

    //School type: 0 for municipal, 1 for subsidized, 2 for private
    private int schoolType;

    //School name
    private String schoolName;

    //Year of graduation
    private int graduationYear;

}
