package TopEducation.TopEducationApp.services;


import TopEducation.TopEducationApp.entities.StudentEntity;
import TopEducation.TopEducationApp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service

//Part of the business layer
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    //Get all the students
    public ArrayList<StudentEntity> getAllStudents() {
        return (ArrayList<StudentEntity>) studentRepository.findAll();
    }
}
