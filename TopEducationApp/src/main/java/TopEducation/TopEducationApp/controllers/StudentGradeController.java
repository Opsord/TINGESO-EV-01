package TopEducation.TopEducationApp.controllers;

import TopEducation.TopEducationApp.entities.StudentScoreEntity;
import TopEducation.TopEducationApp.services.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping

public class StudentGradeController {

    @Autowired
    StudentGradeService studentGradeService;

    @GetMapping("/studentGradeList")
    public String listStudentGrades(Model model) {
        ArrayList<StudentScoreEntity> studentGrades = studentGradeService.getAllStudentGrades();
        model.addAttribute("studentGrades", studentGrades);
        return "studentGradeList";
    }

    // Student creator page
    @GetMapping("/scoreCreator")
    public String createStudentGrade() {
        return "scoreCreator";
    }

    // Create a student and save it to the database
    @PostMapping("/scoreCreator")
    public String createStudentGrade(StudentScoreEntity studentGrade, Model model) {
        studentGradeService.saveStudentGrade(studentGrade);
        return "redirect:/studentGradeList";
    }

    @GetMapping("/studentGradeList/{id}/delete")
    public String deleteStudentGrade(StudentScoreEntity studentGrade, Model model) {
        studentGradeService.deleteStudentGrade(studentGrade.getId());
        return "redirect:/studentGradeList";
    }

    @GetMapping("/studentGradeList/deleteAll")
    public String deleteAllStudentGrades(Model model) {
        studentGradeService.deleteAllStudentGrades();
        return "redirect:/studentGradeList";
    }
}
