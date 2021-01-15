package eservis.app.web.dto;

import eservis.app.model.Course;

public class CourseDTO {
	
	private Long id;
	private String name;
	private int espb;
	private String semester;
	private TeacherDTO teacher;
	
	public CourseDTO() {
		
	}
	
//	public CourseDTO(Course course) {
//		this(course.getId(), course.getName(), course.getEspb(), course.getSemester());
//	}
	
	public CourseDTO(Course course) {
		super();
		this.id = course.getId();
		this.name = course.getName();
		this.espb = course.getEspb();
		this.semester = course.getSemester();
		this.teacher = new TeacherDTO(course.getTeacher());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TeacherDTO getTeacher() {
		return teacher;
	}

	public void setTeacher(TeacherDTO teacher) {
		this.teacher = teacher;
	}
	
	

}