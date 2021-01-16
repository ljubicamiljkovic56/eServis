package eservis.app.web.dto;

import eservis.app.model.Type;

public class TypeDTO {
	
	private Long id;
	
	private String name;
	
	public TypeDTO() {
		
	}
	
	public TypeDTO(Type type) {
		super();
		this.id = type.getId();
		this.name = type.getName();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
