package TopEducation.TopEducationApp.controllers;

import TopEducation.TopEducationApp.services.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping

public class FileManagerController {

    @GetMapping("/fileManager")
    public String fileUploadForm() {
        return "fileManager";
    }

    @Autowired
    FileManagerService fileManagerService;

    @Autowired
    public void FileController(FileManagerService fileManagerService) {
        this.fileManagerService = fileManagerService;
    }

    @PostMapping("/upload")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            // Save the Excel file data to the database
            fileManagerService.saveExcelDataStudentGrade(file);
            fileManagerService.saveExcelDataStudent(file);

            // Update the students info
            fileManagerService.updateStudentsInfo();
            // Redirect to the students list page
            return "redirect:/studentList";
        } catch (IOException e) {
            // Handle the exception and return an error response
            return "redirect:/fileManager";
        }
    }

}
