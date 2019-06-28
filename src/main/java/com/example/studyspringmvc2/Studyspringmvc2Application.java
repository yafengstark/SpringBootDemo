package com.example.studyspringmvc2;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.studyspringmvc2.mapper")
public class Studyspringmvc2Application {

	public static void main(String[] args) {
		SpringApplication.run(Studyspringmvc2Application.class, args);

	}
}
