package com.example.Planner;

import com.example.Planner.Models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannerApplication.class, args);
		System.out.println(PlannerApplication.class.getDeclaredField(name));
	}

}
