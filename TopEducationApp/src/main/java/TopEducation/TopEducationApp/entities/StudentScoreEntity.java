package TopEducation.TopEducationApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "scores")
@Data
@NoArgsConstructor
@AllArgsConstructor

// Part of the persistence layer
public class StudentScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    // RUT is a unique identifier for Chilean citizens
    private String scoreRUT;

    private int score;

    private LocalDate examDate;

    private String studentName;

    private String studentLastName;
}
