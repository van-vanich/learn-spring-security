package com.example.study.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> studentRepository.saveAll(List.of(
                new Student(1, "Ivan Panakhno"),
                new Student(2, "Vova Babak"),
                new Student(3, "Dima Landiak")
        ));
    }
}
