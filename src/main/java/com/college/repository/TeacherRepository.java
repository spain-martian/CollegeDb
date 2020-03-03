package com.college.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.college.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	default void safeDelete(Teacher teacher) {
		for (int i = teacher.getCourses().size() - 1; i >= 0; i--) {
			teacher.removeCourse(teacher.getCourses().get(i));
		}
		delete(teacher);
	}

}