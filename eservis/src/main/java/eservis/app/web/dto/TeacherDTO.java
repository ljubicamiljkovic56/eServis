package eservis.app.web.dto;

import eservis.app.model.Teacher;

public class TeacherDTO {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String title;
	
	public TeacherDTO() {
		
	}

	public TeacherDTO(Teacher teacher) {
		this(teacher.getId(), teacher.getFirstName(), teacher.getLastName(),teacher.getTitle());
	}

	public TeacherDTO(Long id, String firstName, String lastName, String title) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}