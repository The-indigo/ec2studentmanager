package com.example.studentmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentmanager.models.Student;
import com.example.studentmanager.repositories.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private StudentRepository studentRepository;
  
//read    
@GetMapping("/all")
public Iterable<Student> getAllStudents(){
    return studentRepository.findAll();
}
//create
@PostMapping("/add")
public ResponseEntity<?> addStudent(@RequestBody Student student ) throws Exception{
    var fullname= student.getFullName();
    var department=student.getDepartment();
    var studentNumber=student.getStudentNumber();
    //check for empty field
    if(fullname.isBlank() || department.isBlank() || studentNumber.isBlank()){
        return ResponseEntity.status(401).body("All fields are required..");
    }
    //checking the db to see if the student number value already exist
    var numberExists=studentRepository.findByStudentNumber(studentNumber);
    if (numberExists!=null){
        return ResponseEntity.status(401).body("Student number already created....");
    }
    //go ahead and save the data in db if all checks passed
    Student saveStudent = new Student(studentNumber,fullname,department);
    saveStudent  = studentRepository.save(saveStudent);
    return ResponseEntity.status(200).body("Student addedd successfully...");
}


//update
@PostMapping("/update/{studentnumber}")
public ResponseEntity<?> updateStudent(@PathVariable("studentnumber") String studentNumber,
@RequestBody Student student) throws Exception{
    //query the db for the student number
    var studentToUpdate= studentRepository.findByStudentNumber(studentNumber);
    if(studentToUpdate==null){
        return ResponseEntity.status(401).body("Student not found");
    }
    //tenary operator to check if the requested field is empty
    //if empty, we will use the old value in the db
    var fullName=student.getFullName().isBlank()?studentToUpdate.getFullName():student.getFullName();
    var department=student.getDepartment().isBlank()?studentToUpdate.getDepartment():student.getDepartment();
    
    //save the data
    Student studentObject=new Student(studentToUpdate.getId(),studentToUpdate.getStudentNumber(), fullName,department);
    studentObject=studentRepository.save(studentObject);
    return ResponseEntity.status(200).body(studentObject);
}

//delete
@DeleteMapping("/delete/{studentnumber}")
public void deleteStudent(@PathVariable("studentnumber") String studentNumber){
    var student= studentRepository.findByStudentNumber(studentNumber);
    studentRepository.delete(student);
}

}