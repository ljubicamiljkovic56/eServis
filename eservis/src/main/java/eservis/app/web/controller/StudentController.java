package eservis.app.web.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eservis.app.model.Authority;
import eservis.app.model.Document;
import eservis.app.model.Enrollment;
import eservis.app.model.Exam;
import eservis.app.model.Payment;
import eservis.app.model.Student;

import eservis.app.model.User;
import eservis.app.service.AuthorityService;
import eservis.app.service.StudentService;
import eservis.app.service.UserService;
import eservis.app.web.dto.CourseDTO;
import eservis.app.web.dto.DocumentDTO;
import eservis.app.web.dto.EnrollmentDTO;
import eservis.app.web.dto.ExamDTO;
import eservis.app.web.dto.ExamPeriodDTO;
import eservis.app.web.dto.PaymentDTO;
import eservis.app.web.dto.StudentDTO;
import eservis.app.web.dto.TypeDTO;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorityService authorityService;
	
	//svi studenti
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		List<Student> students = studentService.findAll();
		List<StudentDTO> studentsDTO = new ArrayList<>();
		for (Student s : students) {
			studentsDTO.add(new StudentDTO(s));
		}
		return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getStudentsPage(Pageable page) {
		Page<Student> students = studentService.findAll(page);
		List<StudentDTO> studentsDTO = new ArrayList<>();
		for (Student s : students) {
			studentsDTO.add(new StudentDTO(s));
		}
		return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
	}
	
	//student po id-u
	@RequestMapping(value = "studentDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id){
		Student student = studentService.findOne(id);
		if(student == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
	}
	
	//student po userid-u
	@RequestMapping(value = "studentDetailsUserId/{userid}", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> getStudentByUserId(@PathVariable("userid") Long id){
		
		User user = userService.findOne(id);
		long userId = user.getId();
		Student student = new Student();
		
		
		try {
			Connection con = null;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tseo?useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
			Statement stmt = con.createStatement();
			String sql = 
					"select * from student WHERE student.user_id = " + userId + "";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				student.setId(rs.getLong("id"));
				student.setCardNumber(rs.getString("card_number"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<StudentDTO>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){		
		
		Authority authority = authorityService.findOne((long) 2);
		
		
		Student student = new Student();
		student.setCardNumber(studentDTO.getCardNumber());
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		
		User user = new User();
		user.setUsername(student.getCardNumber());
		user.setPassword("123");
		user.setAuthority(authority);
		userService.save(user);
		
		student.setUser(user);
		student = studentService.save(student);
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.CREATED);	
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "updateStudent/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") long id, @RequestBody StudentDTO studentDTO){
		
		Student student = studentService.findOne(studentDTO.getId()); 
		if (student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		student.setCardNumber(studentDTO.getCardNumber());
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		
		student = studentService.save(student);
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);	
	}
	
	@RequestMapping(value = "deleteStudent/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
		Student student = studentService.findOne(id);
		if (student != null){
			studentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/findCard", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> getStudentByCard(
			@RequestParam String cardNumber) {
		Student student = studentService.findByCard(cardNumber);
		if(student == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findLastName", method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getStudentsByLastName(
			@RequestParam String lastName) {
		List<Student> students = studentService.findByLastName(lastName);
		
		List<StudentDTO> studentsDTO = new ArrayList<>();
		for (Student s : students) {
			studentsDTO.add(new StudentDTO(s));
		}
		return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
	}	
	
	//sta student pohadja od predmeta
	@RequestMapping(value = "/{studentId}/enrollments", method = RequestMethod.GET)
	public ResponseEntity<List<EnrollmentDTO>> getStudentEnrollments(
			@PathVariable Long studentId) {
		Student student = studentService.findOne(studentId);
		Set<Enrollment> enrollments = student.getEnrollments();
		List<EnrollmentDTO> enrollmentsDTO = new ArrayList<>();
		for (Enrollment e: enrollments) {
			EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
			enrollmentDTO.setId(e.getId());
			enrollmentDTO.setStartDate((Date) e.getStartDate());
			enrollmentDTO.setEndDate((Date) e.getEndDate());
			enrollmentDTO.setCourse(new CourseDTO(e.getCourse()));
			
			enrollmentsDTO.add(enrollmentDTO);
		}
		return new ResponseEntity<>(enrollmentsDTO, HttpStatus.OK);
	}
	
	//studentovi ispiti
	@RequestMapping(value = "/{studentId}/exams", method = RequestMethod.GET)
	public ResponseEntity<List<ExamDTO>> getStudentExams(
			@PathVariable Long studentId) {
		Student student = studentService.findOne(studentId);
		Set<Exam> exams = student.getExams();
		List<ExamDTO> examsDTO = new ArrayList<>();
		for (Exam e: exams) {
			ExamDTO examDTO = new ExamDTO();
			examDTO.setId(e.getId());
			examDTO.setExamPoints(e.getExamPoints());
			examDTO.setLabPoints(e.getLabPoints());
			examDTO.setDatum(e.getDatum());
			examDTO.setCourse(new CourseDTO(e.getCourse()));
			examDTO.setExamPeriod(new ExamPeriodDTO(e.getExamPeriod()));
		
			examsDTO.add(examDTO);
		}
		return new ResponseEntity<>(examsDTO, HttpStatus.OK);
	}


	//studentove uplate
	@RequestMapping(value = "/{studentId}/payments", method = RequestMethod.GET)
	public ResponseEntity<List<PaymentDTO>> getStudentPayments(@PathVariable Long studentId) {
		
		Student student = studentService.findOne(studentId);
		Set<Payment> payments = student.getPayments();
		List<PaymentDTO> paymentsDTO = new ArrayList<>();
		for (Payment p : payments) {
			PaymentDTO paymentDTO = new PaymentDTO();
			paymentDTO.setId(p.getId());
			paymentDTO.setSvrha(p.getSvrha());
			paymentDTO.setAmount(p.getAmount());
			paymentDTO.setDatum(p.getDatum());
		
			paymentsDTO.add(paymentDTO);
		}
		return new ResponseEntity<>(paymentsDTO, HttpStatus.OK);
	}
	
	//dokumenta studenta
	@RequestMapping(value = "/{studentId}/documents", method = RequestMethod.GET)
	public ResponseEntity<List<DocumentDTO>> getStudentDocuments(@PathVariable Long studentId) {
		
		Student student = studentService.findOne(studentId);
		Set<Document> documents = student.getDocuments();
		List<DocumentDTO> documentsDTO = new ArrayList<>();
		for (Document d : documents) {
			DocumentDTO documentDTO = new DocumentDTO();
			documentDTO.setId(d.getId());
			documentDTO.setStudent(new StudentDTO(d.getStudent()));
			documentDTO.setType(new TypeDTO(d.getType()));
			
			documentsDTO.add(documentDTO);
			
		}
		return new ResponseEntity<>(documentsDTO, HttpStatus.OK);
		
	}
}
