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

        // Just for testing purposes
        System.out.println("Here is before the if block");

        // Verify if the Excel file is xlsx type
        if (file.getOriginalFilename().endsWith(".xlsx")) {

            // Just for testing purposes
            System.out.println("Here is before the try block");

            try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
                Sheet sheet = workbook.getSheetAt(0);

                // Getting the students from the Excel file
                Iterator<Row> rowIterator = sheet.iterator();

                // Just for testing purposes
                System.out.println("Here is before the while loop");

                while (rowIterator.hasNext()) {
                    Row currentRow = rowIterator.next();
                    if (currentRow.getRowNum() == 0) {
                        continue; // Skip header row
                    }

                    // Just for testing purposes
                    System.out.println("Here is before getting the student values");

                    // Getting the student values from the Excel file
                    // Just for testing purposes (the values are printed in the console)

                    String rut = currentRow.getCell(1).getStringCellValue();
                    System.out.println(rut);

                    String firstName = currentRow.getCell(2).getStringCellValue();
                    System.out.println(firstName);

                    String lastName = currentRow.getCell(3).getStringCellValue();
                    System.out.println(lastName);

                    // Date format is dd-mm-yyyy
                    // The date is converted to LocalDate format
                    LocalDate birthDate = currentRow.getCell(4).getLocalDateTimeCellValue().toLocalDate();
                    System.out.println(birthDate);

                    String schoolName = currentRow.getCell(5).getStringCellValue();
                    System.out.println(schoolName);

                    String schoolType = currentRow.getCell(6).getStringCellValue();
                    // School type: 0 -> Municipal, 1 -> Subsidized, 2 -> Private
                    int schoolTypeInt = 0;
                    switch (schoolType) {
                        case "Municipal" -> {}
                        case "Subvencionado" -> schoolTypeInt = 1;
                        case "Particular" -> schoolTypeInt = 2;
                    }
                    System.out.println(schoolType);

                    int graduationYear = (int) currentRow.getCell(7).getNumericCellValue();
                    System.out.println(graduationYear);

                    // Just for testing purposes
                    System.out.println("Here is before the creation of the student object");

                    // Create a new student object
                    StudentEntity student = new StudentEntity();
                    student.setRut(rut);
                    student.setFirstName(firstName);
                    student.setLastName(lastName);
                    student.setBirthDate(birthDate);
                    student.setSchoolType(schoolTypeInt);
                    student.setSchoolName(schoolName);
                    student.setGraduationYear(graduationYear);

                    studentRepository.save(student);

                    // Just for testing purposes
                    System.out.println("Here a student was saved");

                    // Verify if the student is valid before saving it to the database

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
