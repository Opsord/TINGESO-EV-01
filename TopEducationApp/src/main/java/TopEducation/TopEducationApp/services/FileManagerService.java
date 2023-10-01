package TopEducation.TopEducationApp.services;

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
import java.util.Iterator;

@Service

// Part of the business layer
// This class is responsible for reading the Excel file and saving the data to
// the database
public class FileManagerService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdministrationOffice administrationOffice;

    // Method to save the data from an Excel file to the database
    @Generated
    public String saveExcelData(MultipartFile file) throws IOException {

        // Verify if the Excel file is xlsx type
        if (file.getOriginalFilename().endsWith(".xlsx")) {
            try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0);

                // Getting the students from the Excel file
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    Row currentRow = rowIterator.next();
                    if (currentRow.getRowNum() == 0) {
                        continue; // Skip header row
                    }

                    // Getting the student values from the Excel file
                    String rut = currentRow.getCell(1).getStringCellValue();
                    String firstName = currentRow.getCell(2).getStringCellValue();
                    String lastName = currentRow.getCell(3).getStringCellValue();
                    String birthDate = currentRow.getCell(4).getStringCellValue();
                    int schoolType = (int) currentRow.getCell(5).getNumericCellValue();
                    String schoolName = currentRow.getCell(6).getStringCellValue();
                    int graduationYear = (int) currentRow.getCell(7).getNumericCellValue();

                    // Create a new student object
                    StudentEntity student = new StudentEntity();
                    student.setRut(rut);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    // Special case for birthdate
                    LocalDate birth = LocalDate.parse(birthDate);
                    student.setBirthDate(birth);
                    student.setSchoolType(schoolType);
                    student.setSchoolName(schoolName);
                    student.setGraduationYear(graduationYear);

                    // Verify if the student is valid before saving it to the database
                    if (administrationOffice.isValidStudent(student)) {
                        studentRepository.save(student);
                    }
                }
            } catch (Exception e) {
                // Handle exception
            }
        } else {
            return "Uploaded file is not an Excel file";
        }
        return "File uploaded successfully";
    }
}
