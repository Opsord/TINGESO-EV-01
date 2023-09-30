package TopEducation.TopEducationApp.services;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.repositories.StudentRepository;

@Service

// Part of the business layer
// This class is responsible for reading the Excel file and saving the data to
// the database
public class ExcelUploadManager {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdministrationOffice administrationOffice;

    // Use workbook object to access the data from the Excel file
    public void saveDataFromExcel(MultipartFile file) throws IOException, InvalidFormatException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

            // Getting the students from the Excel file
            // First sheet: Students parameters
            // Columns: ID, RUT, Name, Last name, Birthdate, School type, School name,
            // Graduation type, Graduation year
            Row[] firstSheet = new Row[0];
            for (Row row : firstSheet) {
                StudentEntity student = new StudentEntity();

                // Getting the student values from the Excel file
                student.setRut(row.getCell(1).getStringCellValue());
                student.setFirstName(row.getCell(2).getStringCellValue());
                student.setLastName(row.getCell(3).getStringCellValue());
                // Special case for birthdate
                LocalDate birthDate = LocalDate.parse(row.getCell(4).getStringCellValue());
                student.setBirthDate(birthDate);
                student.setSchoolType((int) row.getCell(5).getNumericCellValue());
                student.setSchoolName(row.getCell(6).getStringCellValue());
                student.setGraduationYear((int) row.getCell(7).getNumericCellValue());

                // Other sheets: Student scores from the exams
                // Columns: ID, RUT, Score, Exam date, Name, Last name

                // For each row in the other sheets
                // Generate an average score for the student
                // Save the average score to the database

                // TODO: Average score calculation


                // Verify if the student is valid before saving it to the database
                if (administrationOffice.isValidStudent(student)) {
                    studentRepository.save(student);
                }
            }



        } catch (Exception e) {
            // Handle exception
        }

    }

}
