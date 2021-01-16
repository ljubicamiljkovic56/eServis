package eservis.app.web.dto;

import eservis.app.model.Document;

public class DocumentDTO {

	private Long id;
	private StudentDTO student;
	private TypeDTO type;
	
	public DocumentDTO() {
		
	}
	
	public DocumentDTO(Document document) {
		super();
		this.id = document.getId();
		this.student = new StudentDTO(document.getStudent());
		this.type = new TypeDTO(document.getType());
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
	public TypeDTO getType() {
		return type;
	}
	public void setType(TypeDTO type) {
		this.type = type;
	}
}
