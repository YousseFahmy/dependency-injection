package com.talent.dependency_injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(DependencyInjectionApplication.class, args);
		
		CourseService courseService = appContext.getBean(CourseService.class);
		System.out.println(courseService.getRecommendedCourses());
	}

}
