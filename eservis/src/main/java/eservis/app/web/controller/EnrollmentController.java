package eservis.app.web.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eservis.app.model.Course;
import eservis.app.model.Enrollment;
import eservis.app.model.Student;
import eservis.app.service.CourseService;
import eservis.app.service.EnrollmentService;
import eservis.app.service.StudentService;
import eservis.app.web.dto.EnrollmentDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/enrollments")
public class EnrollmentController {

	@Autowired
	EnrollmentService enrollmentService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	CourseService courseService;
	
	//get all
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EnrollmentDTO>> getEnrollments(){
		List<Enrollment> enrollments = enrollmentService.findAll();
		List<EnrollmentDTO> enrollmentsDTO = new ArrayList<EnrollmentDTO>();
		for(Enrollment en : enrollments) {
			enrollmentsDTO.add(new EnrollmentDTO(en));
		}
		return new ResponseEntity<List<EnrollmentDTO>>(enrollmentsDTO, HttpStatus.OK);
	}
	
	//po id-u
	@RequestMapping(value = "enrollmentDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<EnrollmentDTO> getEnrollment(@PathVariable Long id){
		Enrollment enrollment = enrollmentService.findOne(id);
		if(enrollment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new EnrollmentDTO(enrollment), HttpStatus.OK);
	}
	
	//dodaj pohadjanje kursa
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<EnrollmentDTO> createEnrollment(
			@RequestBody EnrollmentDTO enrollmentDTO) {
		//a new enrollment must have student and course defined
		if (enrollmentDTO.getStudent() == null || enrollmentDTO.getCourse() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		Student student =  studentService.findOne(enrollmentDTO.getStudent().getId());
		Course course = courseService.findOne(enrollmentDTO.getCourse().getId());		
		if (student == null || course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		
		Enrollment enrollment = new Enrollment();
		enrollment.setStartDate((Date) enrollmentDTO.getStartDate());
		enrollment.setEndDate((Date) enrollmentDTO.getEndDate());
		enrollment.setStudent(student);
		enrollment.setCourse(course);
		
		enrollment = enrollmentService.save(enrollment);				
		return new ResponseEntity<>(new EnrollmentDTO(enrollment), HttpStatus.CREATED);	
	}
	
	
	//izmeni pohadjanje kursa
	@RequestMapping(value = "updateEnrollment/{id}", method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<EnrollmentDTO> updateEnrollment(@PathVariable("id") long id,
			@RequestBody EnrollmentDTO enrollmentDTO){
		//an enrollment must exist
		Enrollment enrollment = enrollmentService.findOne(enrollmentDTO.getId()); 
		if (enrollment == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		//we allow changing startDate and endDate for an enrollment only
		enrollment.setStartDate((Date) enrollmentDTO.getStartDate());
		enrollment.setEndDate((Date) enrollmentDTO.getEndDate());
	
		enrollment = enrollmentService.save(enrollment);
		return new ResponseEntity<>(new EnrollmentDTO(enrollment), HttpStatus.OK);	
	}
	
	
	//obrisi pohadjanje kursa
	@RequestMapping(value = "deleteEnrollment/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id){
		Enrollment enrollment = enrollmentService.findOne(id);
		if (enrollment != null){
			enrollmentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
