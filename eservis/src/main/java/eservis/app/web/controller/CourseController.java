package eservis.app.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eservis.app.model.Course;
import eservis.app.model.Enrollment;
import eservis.app.model.Exam;
import eservis.app.service.CourseService;
import eservis.app.web.dto.CourseDTO;
import eservis.app.web.dto.EnrollmentDTO;
import eservis.app.web.dto.ExamDTO;
import eservis.app.web.dto.ExamPeriodDTO;
import eservis.app.web.dto.StudentDTO;

@RestController
@RequestMapping(value="api/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	//svi kursevi
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getCourses() {
		List<Course> courses = courseService.findAll();
		//convert courses to DTOs
		List<CourseDTO> coursesDTO = new ArrayList<CourseDTO>();
		for (Course s : courses) {
			coursesDTO.add(new CourseDTO(s));
		}
		return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
	}
	
	//po id-u
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id){
		Course course = courseService.findOne(id);
		if(course == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new CourseDTO(course), HttpStatus.OK);
	}
	
	//novi kurs
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseDTO courseDTO){
		Course course = new Course();
		course.setName(courseDTO.getName());
	
		course = courseService.save(course);
		return new ResponseEntity<>(new CourseDTO(course), HttpStatus.CREATED);	
	}
	
	//izmeni kurs
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO){
		//a course must exist
		Course course = courseService.findOne(courseDTO.getId()); 
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		course.setName(courseDTO.getName());
	
		course = courseService.save(course);
		return new ResponseEntity<>(new CourseDTO(course), HttpStatus.OK);	
	}
	
	
	//obrisi kurs
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
		Course course = courseService.findOne(id);
		if (course != null){
			courseService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//
	@RequestMapping(value = "/{courseId}/students", method = RequestMethod.GET)
	public ResponseEntity<List<EnrollmentDTO>> getCourseStudents(
			@PathVariable Long courseId) {
		Course course = courseService.findOne(courseId);
		Set<Enrollment> enrollments = course.getEnrollments();
		List<EnrollmentDTO> enrollmentsDTO = new ArrayList<>();
		for (Enrollment e: enrollments) {
			EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
			enrollmentDTO.setId(e.getId());
			enrollmentDTO.setStartDate(e.getStartDate());
			enrollmentDTO.setEndDate(e.getEndDate());
			enrollmentDTO.setStudent(new StudentDTO(e.getStudent()));
			//we leave course field empty
			
			enrollmentsDTO.add(enrollmentDTO);
		}
		return new ResponseEntity<>(enrollmentsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{courseId}/exams", method = RequestMethod.GET)
	public ResponseEntity<List<ExamDTO>> getStudentExams(
			@PathVariable Long courseId) {
		Course course = courseService.findOne(courseId);
		Set<Exam> exams = course.getExams();
		List<ExamDTO> examsDTO = new ArrayList<>();
		for (Exam e: exams) {
			ExamDTO examDTO = new ExamDTO();
			examDTO.setId(e.getId());
			examDTO.setExamPoints(e.getExamPoints());
			examDTO.setLabPoints(e.getLabPoints());
			examDTO.setDate(e.getDate());
			examDTO.setStudent(new StudentDTO(e.getStudent()));
			examDTO.setExamPeriod(new ExamPeriodDTO(e.getExamPeriod()));
		
			examsDTO.add(examDTO);
		}
		return new ResponseEntity<>(examsDTO, HttpStatus.OK);
	}

}
