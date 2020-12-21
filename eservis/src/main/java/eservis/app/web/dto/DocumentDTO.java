package eservis.app.web.dto;

import eservis.app.model.Document;
import eservis.app.model.DocumentType;

public class DocumentDTO {
	
	private Long id;
	
	private StudentDTO student;
	
	private DocumentType documentType;
	
	
	public DocumentDTO() {
		
	}
	

	public DocumentDTO(Long id, StudentDTO student, DocumentType documentType) {
		super();
		this.id = id;
		this.student = student;
		this.documentType = documentType;
	}
	
	public DocumentDTO(Document document) {
		id = document.getId();
		student = new StudentDTO(document.getStudent());
		documentType = document.getDocumentType();
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


	public DocumentType getDocumentType() {
		return documentType;
	}


	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

}