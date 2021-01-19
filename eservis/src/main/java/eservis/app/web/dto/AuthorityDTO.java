package eservis.app.web.dto;

import eservis.app.model.Authority;

public class AuthorityDTO {
	
	private Long id;
	
	String name;
	
	public AuthorityDTO() {
		
	}

	public AuthorityDTO(Authority authority) {
		super();
		this.id = authority.getId();
		this.name = authority.getName();
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
