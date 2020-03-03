package com.college.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.college.model.Course;
import com.college.model.Student;
import com.college.model.Teacher;

public interface StudentRepository extends JpaRepository<Student, Long> {
	 
	@Query("Select distinct s.id from Student s join s.courses c where c.teacher.id = ?1")
	List<Long> getTeachersStudents(long teacherId);
	
	default void safeDelete(Student student) {
		for (int i = student.getCourses().size() - 1; i >= 0; i--) {
			student.removeCourse(student.getCourses().get(i));
	  	}
		delete(student);
	}
}