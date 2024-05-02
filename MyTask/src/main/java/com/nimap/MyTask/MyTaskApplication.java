package com.nimap.MyTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@ComponentScan("com")
@EntityScan("com")
public class MyTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTaskApplication.class, args);
	}
	

}
