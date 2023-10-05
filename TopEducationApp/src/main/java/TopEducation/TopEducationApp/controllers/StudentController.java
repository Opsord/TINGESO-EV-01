package TopEducation.TopEducationApp.controllers;

import TopEducation.TopEducationApp.entities.InstallmentEntity;
import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.services.AdministrationOffice;
import TopEducation.TopEducationApp.services.InstallmentService;
import TopEducation.TopEducationApp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping

// Part of the presentation layer
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    InstallmentService installmentService;

    @Autowired
    AdministrationOffice administrationOffice;

    // Get all students from the database
    @GetMapping("/studentList")
    public String listStudents(Model model) {
        ArrayList<StudentEntity> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "studentList";
    }

    // Student creator page
    @GetMapping("/studentCreator")
    public String createStudent(Model model) {
        return "studentCreator";
    }

    // Create a student and save it to the database
    @PostMapping("/studentCreator")
    public String createStudent(StudentEntity student, Model model) {
        studentService.saveStudent(student);
        return "redirect:/studentList";
    }

    // Delete a student from the database
    @GetMapping("/studentList/{id}/delete")
    public String deleteStudent(StudentEntity student, Model model) {
        studentService.deleteStudent(student.getId());
        return "redirect:/studentList";
    }

    // Delete all students from the database
    @GetMapping("/studentList/deleteAll")
    public String deleteAllStudents(Model model) {
        studentService.deleteAllStudents();
        return "redirect:/studentList";
    }

    // Student detail page
    @GetMapping("/studentList/{id}/details")
    public String showStudentDetails(@PathVariable Long id, Model model) {
        // Get student by id
        StudentEntity student = studentService.findById(id);
        administrationOffice.updateStudentInstallments(student);
        model.addAttribute("student", student);
        // Get installments by student RUT
        ArrayList<InstallmentEntity> installments = installmentService.findAllByInstallmentRUT(student.getRut());
        model.addAttribute("installments", installments);
        // Get the total amount to pay
        return "studentDetails";
    }

}