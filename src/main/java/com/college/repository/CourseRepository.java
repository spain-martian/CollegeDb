package com.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.model.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {
	
	default void safeDelete(Course c) {
		if (c.getTeacher() != null) {
			c.getTeacher().removeCourse(c);
		}
		for (int i = c.getStudents().size() - 1; i >= 0; i--) {
			c.getStudents().get(i).removeCourse(c);
	  	}
	  	delete(c);
	}
}