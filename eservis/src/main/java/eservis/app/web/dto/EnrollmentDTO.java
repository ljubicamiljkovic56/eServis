package eservis.app.web.dto;

import java.util.Date;

import eservis.app.model.Enrollment;

public class EnrollmentDTO {

	private Long id;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private StudentDTO student;
	private CourseDTO course;
	
	public EnrollmentDTO() {
		
	}
	
	public EnrollmentDTO(Enrollment enrollment) {
		id = enrollment.getId();
		startDate = (java.sql.Date) enrollment.getStartDate();
		endDate = (java.sql.Date) enrollment.getEndDate();
		student = new StudentDTO(enrollment.getStudent());
		course = new CourseDTO(enrollment.getCourse());
	}	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	public CourseDTO getCourse() {
		return course;
	}
	public void setCourse(CourseDTO course) {
		this.course = course;
	}	
}