package com.gurshobit.collegefestival.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gurshobit.collegefestival.entities.Student;
import com.gurshobit.collegefestival.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/")
    public String studentIndex(Model studentModelView) {
        return "redirect:/students/list";
    }

    @RequestMapping("/list")
    public String getAllStudents(Model studentModelView) {
        List<Student> res = studentService.getAllStudents();
        studentModelView.addAttribute("allStudents", res);
        return "students-list";
    }

    @RequestMapping("/add")
    public String addStudent(Model studentModelView) {
        Student student = new Student();
        studentModelView.addAttribute("singleStudent", student);
        studentModelView.addAttribute("formMode", "create");
        return "student-form";
    }

    @RequestMapping("/update")
    public String updateStudent(@RequestParam("id") int id, Model studentModelView) {
        Optional<Student> student = studentService.getStudentById(id);
        if(student.isPresent()){
            studentModelView.addAttribute("singleStudent", student.get());
            studentModelView.addAttribute("formMode", "update");
            return "student-form";
        }
        studentModelView.addAttribute("msg", "Student your trying to edit Doesn't Exist");
        return "404";
    }

    @RequestMapping("/delete")
    public String deleteStudent(@RequestParam("id") int id, Model studentModelView) {
        Optional<Student> student = studentService.deleteStudentById(id);
        if(student.isPresent()){
            System.out.println((student.isPresent() ? "Deleted Student Id " + student.get().getId() : "Invalid Id"));
            return "redirect:/students/";
        }

        studentModelView.addAttribute("msg", "Student your trying to delete Doesn't Exist");
        return "404";
    }

    @PostMapping("/save")
    public String saveStudent(@RequestParam(value = "id", required = false,defaultValue = "0") String stuId,
                              @RequestParam("firstName") String studentFirstName,
                              @RequestParam("lastName") String studentLastName,
                              @RequestParam("course") String courseName,
                              @RequestParam("country") String countryName) {
        Student student;
        int studentId = Integer.parseInt(stuId);
        if (studentId != 0) {
            Optional<Student> optionalStudent = studentService.getStudentById(studentId);
            student = (optionalStudent.isPresent()) ? optionalStudent.get() : new Student();
        } else {
            student = new Student();
        }

        student.setFirstName(studentFirstName);
        student.setLastName(studentLastName);
        student.setCourse(courseName);
        student.setCountry(countryName);
        studentService.save(student);
        return "redirect:/students/";
    }
}
