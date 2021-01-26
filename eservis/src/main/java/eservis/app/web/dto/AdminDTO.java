package eservis.app.web.dto;

import eservis.app.model.Admin;

public class AdminDTO {
	
	private Long id;
	private String firstname;
	private String lastname;
	
	public AdminDTO() {
		
	}

	public AdminDTO(Admin admin) {
		super();
		this.id = admin.getId();
		this.firstname = admin.getFirstname();
		this.lastname = admin.getLastname();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}