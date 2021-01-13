package eservis.app.web.controller;

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
import org.springframework.web.bind.annotation.RestController;

import eservis.app.model.Course;
import eservis.app.model.Teacher;
import eservis.app.service.TeacherService;
import eservis.app.web.dto.CourseDTO;
import eservis.app.web.dto.TeacherDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/teachers")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	//svi nastavnici
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
		List<Teacher> teachers = teacherService.findAll();
		//convert teachers to DTOs
		List<TeacherDTO> teachersDTO = new ArrayList<>();
		for (Teacher s : teachers) {
			teachersDTO.add(new TeacherDTO(s));
		}
		return new ResponseEntity<>(teachersDTO, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TeacherDTO>> getTeachersPage(Pageable page) {
		Page<Teacher> teachers = teacherService.findAll(page);
		
		//convert teachers to DTOs
		List<TeacherDTO> teachersDTO = new ArrayList<>();
		for (Teacher s : teachers) {
			teachersDTO.add(new TeacherDTO(s));
		}
		return new ResponseEntity<>(teachersDTO, HttpStatus.OK);
	}
	
	
	//nadji nastavnika po id-u
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<TeacherDTO> getTeacher(@PathVariable Long id){
		Teacher teacher = teacherService.findOne(id);
		if(teacher == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new TeacherDTO(teacher), HttpStatus.OK);
	}
	
	//dodaj nastavnika
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<TeacherDTO> saveTeacher(@RequestBody
			TeacherDTO teacherDTO){
		Teacher teacher = new Teacher();
		teacher.setFirstName(teacherDTO.getFirstName());
		teacher.setLastName(teacherDTO.getLastName());
		
		teacher = teacherService.save(teacher);
		return new ResponseEntity<>(new TeacherDTO(teacher), HttpStatus.CREATED);	
	}
	
	
	//izmeni nastavnika
	@RequestMapping(method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO){
		//a teacher must exist
		Teacher teacher = teacherService.findOne(teacherDTO.getId()); 
		if (teacher == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		teacher.setFirstName(teacherDTO.getFirstName());
		teacher.setLastName(teacherDTO.getLastName());
		
		teacher = teacherService.save(teacher);
		return new ResponseEntity<>(new TeacherDTO(teacher), HttpStatus.OK);	
	}
	
	
	//obrisi nastavnika
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTeacher(@PathVariable Long id){
		Teacher teacher = teacherService.findOne(id);
		if (teacher != null){
			teacherService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//svi kursevi na kojima predaje odredjeni nastavnik
	@RequestMapping(value = "/{teacherId}/courses", method = RequestMethod.GET)
	public ResponseEntity<List<CourseDTO>> getTeacherCourses(
			@PathVariable Long teacherId) {
		Teacher teacher = teacherService.findOne(teacherId);
		Set<Course> courses = teacher.getCourses();
		List<CourseDTO> coursesDTO = new ArrayList<>();
		for (Course c: courses) {
			coursesDTO.add(new CourseDTO(c));
		}
		return new ResponseEntity<>(coursesDTO, HttpStatus.OK);
	}

}
