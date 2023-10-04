package TopEducation.TopEducationApp.services;

import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import TopEducation.TopEducationApp.repositories.StudentGradeRepository;
import lombok.Generated;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.repositories.StudentRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

@Service

// Part of the business layer
// This class is responsible for reading the Excel file and saving the data to
// the database
public class FileManagerService {

    @Autowired
    private AdministrationOffice administrationOffice;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    @Autowired
    private StudentService studentService;

    // Method to save the data from an Excel with the students information
    @Generated
    public void saveExcelDataStudent(MultipartFile file) throws IOException {

        // Verify if the Excel file is xlsx type
        if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xlsx")) {

            try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0);

                // Getting the students from the Excel file

                for (Row currentRow : sheet) {
                    if (currentRow.getRowNum() == 0) {
                        continue; // Skip header row
                    }

                    // Getting the student values from the Excel file
                    // Just for testing purposes (the values are printed in the console)

                    String rut = currentRow.getCell(1).getStringCellValue();
                    // System.out.println(rut);

                    String firstName = currentRow.getCell(2).getStringCellValue();
                    // System.out.println(firstName);

                    String lastName = currentRow.getCell(3).getStringCellValue();
                    // System.out.println(lastName);

                    // Date format is dd-mm-yyyy
                    // The date is converted to LocalDate format
                    LocalDate birthDate = currentRow.getCell(4).getLocalDateTimeCellValue().toLocalDate();
                    // System.out.println(birthDate);

                    String schoolName = currentRow.getCell(5).getStringCellValue();
                    // System.out.println(schoolName);

                    String schoolType = currentRow.getCell(6).getStringCellValue();
                    // School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
                    int schoolTypeInt = 0;
                    switch (schoolType) {
                        case "Municipal" -> {
                        }
                        case "Subvencionado" -> schoolTypeInt = 1;
                        case "Particular" -> schoolTypeInt = 2;
                    }
                    // System.out.println(schoolType);

                    int graduationYear = (int) currentRow.getCell(7).getNumericCellValue();
                    // System.out.println(graduationYear);

                    // Create a new student object
                    StudentEntity student = new StudentEntity();
                    student.setRut(rut);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setBirthDate(birthDate);
                    student.setSchoolType(schoolTypeInt);
                    student.setSchoolName(schoolName);
                    student.setGraduationYear(graduationYear);

                    // Verify if the student is valid before saving it to the database
                    if (administrationOffice.isValidStudent(student)) {
                        // Save the student to the database
                        studentRepository.save(student);
                    }

                }
            } catch (Exception e) {
                // Handle exception
            }
        }
    }

    // Method to save the data from an Excel with the students grades information
    @Generated
    public void saveExcelDataStudentGrade(MultipartFile file) throws IOException {

        // Verify if the Excel file is xlsx type
        if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".xlsx")) {

            try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
                // Getting all other sheets than the first one
                for (int i = 1; i < workbook.getNumberOfSheets(); i++) {
                    Sheet sheet = workbook.getSheetAt(i);

                    // Getting the students grades from the Excel file

                    for (Row currentRow : sheet) {
                        if (currentRow.getRowNum() == 0) {
                            continue; // Skip header row
                        }

                        // Getting the student grades values from the Excel file
                        // Just for testing purposes (the values are printed in the console)

                        String gradeRUT = currentRow.getCell(1).getStringCellValue();
                        // System.out.println(gradeRUT);

                        int score = (int) currentRow.getCell(2).getNumericCellValue();
                        // System.out.println(score);

                        // Date format is dd-mm-yyyy
                        // The date is converted to LocalDate format
                        LocalDate examDate = currentRow.getCell(3).getLocalDateTimeCellValue().toLocalDate();
                        // System.out.println(examDate);

                        String studentName = currentRow.getCell(4).getStringCellValue();
                        // System.out.println(studentName);

                        String studentLastName = currentRow.getCell(5).getStringCellValue();
                        // System.out.println(studentLastName);

                        // Create a new student grade object
                        StudentScoreEntity studentGrade = new StudentScoreEntity();
                        studentGrade.setGradeRUT(gradeRUT);
                        studentGrade.setScore(score);
                        studentGrade.setExamDate(examDate);
                        studentGrade.setStudentName(studentName);
                        studentGrade.setStudentLastName(studentLastName);

                        // Verify if the student grade is valid before saving it to the database
                        if (administrationOffice.isValidStudentGrade(studentGrade)) {
                            // Save the student grade to the database
                            studentGradeRepository.save(studentGrade);
                        }

                    }
                }

            } catch (Exception e) {
                // Handle exception
            }
        }
    }

    @Generated
    public void updateStudentsInfo(){
        // Get all the students
        Iterable<StudentEntity> students = studentService.getAllStudents();

        // For each student, update the student info
        for (StudentEntity student : students) {
            // Get the student grades
            Iterable<StudentScoreEntity> studentGrades = studentGradeRepository.findAllGradesByStudentRUT(student.getRut());

            // Calculate the average score
            double averageScore = 0;
            int count = 0;
            for (StudentScoreEntity studentGrade : studentGrades) {
                averageScore += studentGrade.getScore();
                count++;
            }
            averageScore = (int)(averageScore/count);

            // Update the student info
            student.setAverageGrade(averageScore);

            // Save the student to the database
            studentRepository.save(student);
        }
    }
}
