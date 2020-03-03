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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Teacher implements Serializable {
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

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Course> courses = new ArrayList<>();

	public void addCourse(Course course) {
		courses.add(course);
		course.setTeacher(this);
	}

	public void removeCourse(Course course) {
		System.out.println("rem");
		if (courses.remove(course)) {
			course.setTeacher(null);
		}
	}


	// -------------------------------------------

	public Teacher() {
		super();
	}

	public Teacher(String firstname, String lastname, Address address) {
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

	public List<Course> getCourses() {
		return courses;
	}

	// --------------------------------------
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Teacher))
			return false;
		return id != null && id.equals(((Teacher) o).getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Teacher (" + id + ", " + firstname + ", " + lastname + ", " + address + ", " + courses + ")";
	}

}