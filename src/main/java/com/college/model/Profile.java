package com.college.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Column(length = 200)
	private String preferencies;

	//-----------------------  @OneToOne
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Student student;
	
	

	// ---------------------Standard -----------
	public Profile() {
		super();
	}

	public Profile(Date birthdate, String preferencies) {
		this.birthdate = birthdate;
		this.preferencies = preferencies;
	}

	public Long getId() {
		return id;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPreferencies() {
		return preferencies;
	}

	public void setPreferencies(String preferencies) {
		this.preferencies = preferencies;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Profile= (" + id + ", " + (new SimpleDateFormat("dd-MM-yyyy")).format(birthdate) + ", " + preferencies + ")";
	}
	
	

}
