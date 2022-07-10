package com.gurshobit.collegefestival.services;

import com.gurshobit.collegefestival.entities.Student;
import com.gurshobit.collegefestival.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Optional<Student> getStudentById(int id){
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student save(Student student){
        return studentRepository.saveAndFlush(student);
    }

    public Optional<Student> deleteStudentById(int id){
        Optional <Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            studentRepository.deleteById(id);
        }

        return student;
    }
}
