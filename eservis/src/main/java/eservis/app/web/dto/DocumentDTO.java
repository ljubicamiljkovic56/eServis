package eservis.app.web.dto;

public class DocumentDTO {
	
	private Long id;
	
	private StudentDTO student;
	
	
	public DocumentDTO() {
		
	}
	

	public DocumentDTO(Long id, StudentDTO student) {
		super();
		this.id = id;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

}