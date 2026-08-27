package com.placementpro.backend.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.placementpro.backend.entity.Student;
import com.placementpro.backend.repository.StudentRepository;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {

        this.studentRepository =
                studentRepository;
    }


    // =========================================
    // REGISTER
    // =========================================

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(
            @RequestBody Student student) {

        Optional<Student> existingStudent =
                studentRepository.findByEmail(
                        student.getEmail()
                );


        if (existingStudent.isPresent()) {

            return ResponseEntity
                    .badRequest()
                    .body(
                        java.util.Map.of(
                            "message",
                            "Email already registered."
                        )
                    );
        }


        Student savedStudent =
                studentRepository.save(student);


        savedStudent.setPassword(null);


        return ResponseEntity.ok(
                savedStudent
        );
    }


    // =========================================
    // LOGIN
    // =========================================

    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(
            @RequestBody Student loginStudent) {


        Optional<Student> studentOptional =
                studentRepository.findByEmail(
                        loginStudent.getEmail()
                );


        if (studentOptional.isEmpty()) {

            return ResponseEntity
                    .status(401)
                    .body(
                        java.util.Map.of(
                            "message",
                            "Invalid email or password."
                        )
                    );
        }


        Student student =
                studentOptional.get();


        if (
            !student.getPassword()
                    .equals(loginStudent.getPassword())
        ) {

            return ResponseEntity
                    .status(401)
                    .body(
                        java.util.Map.of(
                            "message",
                            "Invalid email or password."
                        )
                    );
        }


        // Do not send password to frontend

        student.setPassword(null);


        return ResponseEntity.ok(
                student
        );
    }
    @GetMapping("/{email}")
        public ResponseEntity<?> getStudentProfile(@PathVariable String email) {

    Optional<Student> student =
            studentRepository.findByEmail(email);

    if (student.isEmpty()) {

        return ResponseEntity
                .status(404)
                .body(
                    java.util.Map.of(
                        "message",
                        "Student not found."
                    )
                );
    }

    Student foundStudent = student.get();

    // Never send password to frontend
    foundStudent.setPassword(null);

    return ResponseEntity.ok(foundStudent);
}

}