package com.college;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.college.model.Address;
import com.college.model.Course;
import com.college.model.Profile;
import com.college.model.Student;
import com.college.model.Teacher;
import com.college.repository.CourseRepository;
import com.college.repository.StudentRepository;
import com.college.repository.TeacherRepository;

@SpringBootApplication
public class CollegeDbApplication implements CommandLineRunner {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;

	@PersistenceContext
	EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(CollegeDbApplication.class, args);
	}

	@Override
	@Transactional // for using entityManager.persist(); !!!
	public void run(String... args) throws Exception {
		// Clean up database tables
		
		printDB();
		test2();
		
	}

	private void printDB() {
		// Print database
		System.out.println("---------------------------Database content --------------------------------");
		System.out.println("\nStudents:");
		List<Student> students = studentRepository.findAll();
		students.stream().forEach(System.out::println);
//		for (Student s: students) {
//			System.out.println(s);
//		}

		System.out.println("\nTeachers:");
		List<Teacher> teachers = teacherRepository.findAll();
		teachers.stream().forEach(System.out::println);
//		for (Teacher c: teachers) {
//			System.out.println(c);
//		}

		System.out.println("\nCourses:");
		List<Course> courses = courseRepository.findAll();
		//courses.stream().forEach(s -> System.out.println(s.fullInfoString()));
		for (Course c: courses) {
			System.out.println(c.fullInfoString());
			for (Student s : c.getStudents()) {
				System.out.println("att: " + s);
		  	}
		}
	}

	private void test2() {
		// creating operations
		System.out.println("Add courses:");
		Course c1 = new Course("code001", "Mathematics", 11); 
		Course c2 = new Course("code002", "Physics", 12); 
		Course c3 = new Course("code003", "Java", 13); 
		Course c4 = new Course("code004", "Python", 14); 
		Course c5 = new Course("code005", "Psychology", 15); 
		courseRepository.save(c1);
		courseRepository.save(c2);
		courseRepository.save(c3);
		courseRepository.save(c4);
		courseRepository.save(c5);
		
		
		System.out.println("Add students:");
		
		Student s1 = new Student("Student1", "Lastname1", new Address("Address1", "City1", "Country1", "000001"));
		Student s2 = new Student("Student2", "Lastname2", new Address("Address2", "City2", "Country2", "000002"));
		Student s3 = new Student("Student3", "Lastname3", new Address("Address3", "City3", "Country3", "000003"));
		Student s4 = new Student("Student4", "Lastname4", new Address("Address4", "City4", "Country4", "000004"));
		s1.setProfile(new Profile(new Date(), "Pref1"));
		s2.setProfile(new Profile(new Date(), "Pref2"));
		s3.setProfile(new Profile(new Date(), "Pref3"));
		s4.setProfile(new Profile(new Date(), "Pref4"));
		
		studentRepository.save(s1);
		studentRepository.save(s2);
		studentRepository.save(s3);
		studentRepository.save(s4);
		
		System.out.println("Assign courses to students:");
		s1.addCourse(c1);
		s1.addCourse(c3);
		s1.addCourse(c4);
		
		s2.addCourse(c3);
		s2.addCourse(c5);

		s3.addCourse(c3);
		
		s4.addCourse(c3);
		s4.addCourse(c4);
		studentRepository.save(s1);
		studentRepository.save(s2);
		studentRepository.save(s3);
		studentRepository.save(s4);
		
		System.out.println("Add teachers:");
		
		Teacher t1 = new Teacher("Name1", "Lastname1", new Address("Address1", "City1", "Country1", "000001"));
		Teacher t2 = new Teacher("Name2", "Lastname2", new Address("Address2", "City2", "Country2", "000002"));
		Teacher t3 = new Teacher("Name3", "Lastname3", new Address("Address3", "City3", "Country3", "000003"));

		teacherRepository.save(t1);
		teacherRepository.save(t2);
		teacherRepository.save(t3);
			
	    System.out.println("Assign teachers to courses:");
	    t1.addCourse(c2);
	    t1.addCourse(c3);
	    t2.addCourse(c4);
	    t2.addCourse(c5);
	    printDB();

	    //deleting 
		System.out.println("Remove teacher 1 (remove it from courses first):");
	  	teacherRepository.safeDelete(t1);

	  	System.out.println("Remove course (remove it from teachers and from students first):");
	 	courseRepository.safeDelete(c4);
		
		
		System.out.println("Remove student (remove his courses first):");
		studentRepository.safeDelete(s3);
	
	 	System.out.println("Assign teacher to a course:");
	 	t3.addCourse(c1);
	 	t3.addCourse(c3);

	
		printDB();
		
		System.out.println("Query of all the students that attend the teacher's courses:");

		List<Long> idList = studentRepository.getTeachersStudents(t3.getId());
		idList.stream().forEach(System.out::println);
	
	}
		
}
