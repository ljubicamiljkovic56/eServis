package eservis.app.web.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eservis.app.model.Admin;
import eservis.app.model.Authority;
import eservis.app.model.User;
import eservis.app.service.AdminService;
import eservis.app.service.AuthorityService;
import eservis.app.service.UserService;
import eservis.app.web.dto.AdminDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/admins")
public class AdminController {

	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	//svi admini
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<AdminDTO>> getAdmins() {
		List<Admin> admins = adminService.findAll();

		List<AdminDTO> adminsDTO = new ArrayList<AdminDTO>();
		for (Admin a : admins) {
			adminsDTO.add(new AdminDTO(a));
		}
		return new ResponseEntity<>(adminsDTO, HttpStatus.OK);
	}
	
	
	//svi admini pageable
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AdminDTO>> getAdminsPage(Pageable page) {
		Page<Admin> admins =  adminService.findAll(page);
		
		List<AdminDTO> adminsDTO = new ArrayList<>();
		for (Admin admin : admins) {
			adminsDTO.add(new AdminDTO(admin));
		}
		return new ResponseEntity<>(adminsDTO, HttpStatus.OK);
	}
	
	//po id-u
	@RequestMapping(value = "adminDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<AdminDTO> getAdmin(@PathVariable Long id){
		Admin admin = adminService.findOne(id);
		if(admin == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.OK);
	}
	
	//po userid-u
	@RequestMapping(value = "adminDetailsUserId/{userid}", method = RequestMethod.GET)
	public ResponseEntity<AdminDTO> getAdminByUserId(@PathVariable("userid") Long id){
		
		User user = userService.findOne(id);
		long userId = user.getId();
		Admin admin = new Admin();
		
		
		try {
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tseo?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
			Statement stmt = con.createStatement();
			String sql = 
					"select * from admin WHERE admin.user_id = " + userId + "";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				admin.setId(rs.getLong("id"));
				admin.setFirstname(rs.getString("firstname"));
				admin.setLastname(rs.getString("lastname"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<AdminDTO>(new AdminDTO(admin), HttpStatus.OK);
	}
	
	//novi admin
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AdminDTO> saveAdmin(@RequestBody AdminDTO adminDTO){
		
		Authority authority = authorityService.findOne((long) 1);
		
		Admin admin = new Admin();
		admin.setFirstname(adminDTO.getFirstname());
		admin.setLastname(adminDTO.getLastname());
		
		User user = new User();
		user.setUsername(admin.getFirstname());
		user.setPassword("123");
		user.setAuthority(authority);
		userService.save(user);
		
		admin.setUser(user);
		admin = adminService.save(admin);
		
		
		return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.CREATED);	
	}
	
	//izmeni admina
	@RequestMapping(value = "updateAdmin/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<AdminDTO> updateAdmin(@PathVariable("id") long id, @RequestBody AdminDTO adminDTO){
		
		Admin admin = adminService.findOne(adminDTO.getId()); 
		if (admin == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		admin.setFirstname(adminDTO.getFirstname());
		admin.setLastname(adminDTO.getLastname());
		admin = adminService.save(admin);
		return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.OK);	
	}
	
	
	//obrisi admina
	@RequestMapping(value = "deleteAdmin/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAdmin(@PathVariable Long id){
		Admin admin = adminService.findOne(id);
		if (admin != null){
			adminService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
