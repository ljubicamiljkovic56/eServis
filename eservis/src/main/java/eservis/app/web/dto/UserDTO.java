package eservis.app.web.dto;

import eservis.app.model.User;

public class UserDTO {

	private Long id;
	
	private String username;
	
	private String password;
	
	private AuthorityDTO authority;
	
	public UserDTO() {
		
	}

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authority = new AuthorityDTO(user.getAuthority());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}	
	
	
	
	
}