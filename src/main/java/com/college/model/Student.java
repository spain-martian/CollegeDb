package com.college.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(nullable = false, length = 40)  
	private String firstname;

	@Column(length = 40)  
	private String lastname;

	@Embedded
	private Address address;

	//------------------- @OneToOne
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "student", orphanRemoval = true)
	private Profile profile;
	

	public void setProfile(Profile profile) {
        if (profile == null) {
            if (this.profile != null) {
                this.profile.setStudent(null);
            }
        } else {
        	profile.setStudent(this);
        }
        this.profile = profile;
    }
	

	public Profile getProfile() {
		return profile;
	}
	
	//--------------------------------- @ManyToMany
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();

	public void addCourse(Course course) {
		courses.add(course);
		course.getStudents().add(this);
	}

	public void removeCourse(Course course) {
		if (courses.remove(course)) {
			System.out.println("rem c from s " + course.getStudents().remove(this));
		}
	}
	
	public List<Course> getCourses() {
		return courses;
	}
		

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Student))
			return false;
		return id != null && id.equals(((Student) o).getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}

	
	//--------------------Standard ---------------------
	public Student() {
		super();
	}


	public Student(String firstname, String lastname, Address address) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}


	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Student= (" + id + ", " + firstname + ", " + lastname + ", " + address + ", " + profile + ", " + courses + ")";
	}
	
	
}
