package com.example.start;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.example.*.dao"})
@ComponentScan(basePackages = {"com.example"})
@EnableAutoConfiguration
public class SpringBootStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStoreApplication.class, args);
	}
}
