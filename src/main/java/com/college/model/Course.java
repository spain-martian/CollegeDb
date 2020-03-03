package com.college.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;


@Entity
@Table
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NaturalId(mutable = false)
	@Column(unique = true, nullable = false, length = 10)
	private String code;

	@Column(nullable = false, length = 100)
	private String title;

	private int classroom;
	
	//---------- @ManyToOne-----------
	@ManyToOne(fetch = FetchType.LAZY)
	private Teacher teacher;
	
	//---------- @ManyToMany--------
	@ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
	
	public List<Student> getStudents() {
		return students;
	}
	

	//-----------------------------Standard ---------------
	public Course() {
		super();
	}
	
	public Course(String code, String title, int classroom) {
		super();
		this.code = code;
		this.title = title;
		this.classroom = classroom;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	
	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getClassroom() {
		return classroom;
	}

	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	//--------------------------------------
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course )) return false;
        return id != null && id.equals(((Course) o).getId());
    }
 
    @Override
    public int hashCode() {
        return 31;
    }


	@Override
	public String toString() {
		return "Course= (" + id + ", " + code + ", " + title + ", " + classroom + ", " + (teacher == null ? "null" : teacher.getId()) + ")";
	}
	
	public String fullInfoString() {
		return "Course= (" + id + ", " + code + ", " + title + ", " + classroom
				+ ", teacher= " + (teacher == null ? "null" : teacher.getFirstname() + " " + teacher.getLastname())
				+ ", nof students= " + students.size()
				+ ")";
	}

}
