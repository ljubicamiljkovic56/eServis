package eservis.app.web.controller;

import java.sql.Date;
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

import eservis.app.model.Course;
import eservis.app.model.Exam;
import eservis.app.model.ExamPeriod;
import eservis.app.model.Student;
import eservis.app.service.CourseService;
import eservis.app.service.ExamPeriodService;
import eservis.app.service.ExamService;
import eservis.app.service.StudentService;
import eservis.app.web.dto.ExamDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/exams")
public class ExamController {
	
	@Autowired
	ExamService examService;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@Autowired
	ExamPeriodService examPeriodService;
	

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ExamDTO>> getExams(){
		List<Exam> exams = examService.findAll();
		List<ExamDTO> examsDTO = new ArrayList<ExamDTO>();
		for(Exam e : exams) {
			examsDTO.add(new ExamDTO(e));
		}
		return new ResponseEntity<List<ExamDTO>>(examsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExamDTO>> getExamsPage(Pageable page) {
		Page<Exam> exams =  examService.findAll(page);
		
		List<ExamDTO> examsDTO = new ArrayList<>();
		for (Exam exam : exams) {
			examsDTO.add(new ExamDTO(exam));
		}
		return new ResponseEntity<>(examsDTO, HttpStatus.OK);
	}
	
	//po id-u
	@RequestMapping(value = "examDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<ExamDTO> getExam(@PathVariable Long id){
		Exam exam = examService.findOne(id);
		if(exam == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
		return new ResponseEntity<>(new ExamDTO(exam), HttpStatus.OK);
	}
	
	//dodaj ispit
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ExamDTO> createExam(@RequestBody ExamDTO examDTO) {
		// a new exam must have student, course and examPeriod defined
		if (examDTO.getStudent() == null || examDTO.getCourse() == null
				|| examDTO.getExamPeriod() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Student student = studentService.findOne(examDTO.getStudent().getId());
		Course course = courseService.findOne(examDTO.getCourse().getId());
		ExamPeriod examPeriod = examPeriodService.findOne(examDTO
				.getExamPeriod().getId());
		if (student == null || course == null || examPeriod == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Exam exam = new Exam();
		exam.setDatum((Date) examDTO.getDatum());
		exam.setExamPoints(examDTO.getExamPoints());
		exam.setLabPoints(examDTO.getLabPoints());
		exam.setStudent(student);
		exam.setCourse(course);
		exam.setExamPeriod(examPeriod);

		exam = examService.save(exam);
		return new ResponseEntity<>(new ExamDTO(exam), HttpStatus.CREATED);
	}

	//izmeni ispit
	@RequestMapping(value = "updateExam/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ExamDTO> updateExam(@PathVariable("id") long id, @RequestBody ExamDTO examDTO) {
		Exam exam = examService.findOne(examDTO.getId());
		if (exam == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		exam.setDatum((Date) examDTO.getDatum());
		exam.setExamPoints(examDTO.getExamPoints());
		exam.setLabPoints(examDTO.getLabPoints());

		exam = examService.save(exam);
		return new ResponseEntity<>(new ExamDTO(exam), HttpStatus.OK);
	}

	//obrisi ispit
	@RequestMapping(value = "deleteExam/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
		Exam exam = examService.findOne(id);
		if (exam != null) {
			examService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
