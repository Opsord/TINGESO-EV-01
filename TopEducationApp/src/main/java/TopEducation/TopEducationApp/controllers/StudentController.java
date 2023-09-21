package TopEducation.TopEducationApp.controllers;


import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping

//Part of the presentation layer
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public String listStudents(Model model) {
        ArrayList<StudentEntity> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "studentsIndex";
    }
}