package com.example.study.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer studentId) {
        return studentRepository.getById(studentId);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository
                .findStudentByStudentName(student.getName());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("student with this email was registered");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Integer studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Integer studentId, String studentName) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new IllegalStateException("student with id " + studentId + " does not exists"));
        if (studentName != null && studentName.length() > 0 && !studentName.equals(student.getName())) {
            student.setName(studentName);
        }
    }
}
