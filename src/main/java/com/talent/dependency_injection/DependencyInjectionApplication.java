package com.talent.dependency_injection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.talent.dependency_injection.entities.Course;
import com.talent.dependency_injection.repositories.CourseRepository;

@SpringBootApplication
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(DependencyInjectionApplication.class, args);
		
		System.out.println("Creating Course");
		Course course = new Course(1, "MySecondCourse", "This is my first course.", 5);
		
		
		System.out.println("Adding Course");
		CourseRepository courseRepo = appContext.getBean(CourseRepository.class);
		// courseRepo.addCourse(course);
		
		System.out.println("Course Added");

		course.setAssessmentId(3);
		courseRepo.updateCourse(course);
		courseRepo.deleteCourse(course);
		System.out.println(courseRepo.getCourseByID(0).getName());
	}

}
