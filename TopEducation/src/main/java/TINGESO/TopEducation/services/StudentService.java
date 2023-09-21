package TINGESO.TopEducation.services;

import TINGESO.TopEducation.entities.StudentEntity;
import TINGESO.TopEducation.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    //Get all the students
    public ArrayList<StudentEntity> getAllStudents() {
        return (ArrayList<StudentEntity>) studentRepository.findAll();
    }
}
