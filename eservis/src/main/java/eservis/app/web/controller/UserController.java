package eservis.app.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eservis.app.model.User;
import eservis.app.service.UserService;
import eservis.app.web.dto.LoginDTO;
import eservis.app.web.dto.UserDTO;
//import eservis.app.web.security.TokenUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private StudentService studentService;
//	
	//@Autowired
	//AuthenticationManager authenticationManager;
	
	//@Autowired
	//private UserDetailsService userDetailsService;
	
	//@Autowired
	//TokenUtils tokenUtils;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> users = userService.findAll();
		List<UserDTO> usersDTO = new ArrayList<>();
		for (User user : users) {
			usersDTO.add(new UserDTO(user));
		}
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/loginUser", consumes = "application/json;charset=UTF-8")
	public ResponseEntity<UserDTO> loginUser(@RequestBody LoginDTO login){
		System.out.println("Login...");
		String username = login.getUsername();
		String password = login.getPassword();
		User user = userService.findByUsernameAndPassword(username, password);	
		
		String uloga;
		if (user == null) {
			System.out.println("Neuspesna prijava");
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.BAD_REQUEST);
		
		} else if (user.getAuthority().getName().equalsIgnoreCase("role_admin")){
			uloga = user.getAuthority().getName();
			System.out.println("Ulogovali ste se kao: " + uloga);
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		} else if (user.getAuthority().getName().equalsIgnoreCase("role_student")) {
			uloga = user.getAuthority().getName();
			System.out.println("Ulogovali ste se kao: " + uloga);
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		} else if (user.getAuthority().getName().equalsIgnoreCase("role_teacher")) {
			uloga = user.getAuthority().getName();
			System.out.println("Ulogovali ste se kao: " + uloga);
			return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
		} else {
			System.out.println("Greska");
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
	
	}
	
//	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
//	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
//        try {
//			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//					loginDTO.getUsername(), loginDTO.getPassword());
//            @SuppressWarnings("unused")
//			Authentication authentication = authenticationManager.authenticate(token);
//            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
//            return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
//        }
//	}

}
