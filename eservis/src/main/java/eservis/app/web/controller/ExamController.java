package eservis.app.web.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		exam.setDate((Date) examDTO.getDate());
		exam.setExamPoints(examDTO.getExamPoints());
		exam.setLabPoints(examDTO.getLabPoints());
		exam.setStudent(student);
		exam.setCourse(course);
		exam.setExamPeriod(examPeriod);

		exam = examService.save(exam);
		return new ResponseEntity<>(new ExamDTO(exam), HttpStatus.CREATED);
	}

	//izmeni ispit
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ExamDTO> updateExam(@RequestBody ExamDTO examDTO) {
		// an exam must exist
		Exam exam = examService.findOne(examDTO.getId());
		if (exam == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// we allow changing date and points for an exam only
		exam.setDate((Date) examDTO.getDate());
		exam.setExamPoints(examDTO.getExamPoints());
		exam.setLabPoints(examDTO.getLabPoints());

		exam = examService.save(exam);
		return new ResponseEntity<>(new ExamDTO(exam), HttpStatus.OK);
	}

	//obrisi ispit
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
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