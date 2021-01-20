package eservis.app.web.controller;

import java.sql.Date;
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

import eservis.app.model.Exam;
import eservis.app.model.ExamPeriod;
import eservis.app.service.ExamPeriodService;
import eservis.app.web.dto.CourseDTO;
import eservis.app.web.dto.ExamDTO;
import eservis.app.web.dto.ExamPeriodDTO;
import eservis.app.web.dto.StudentDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/examperiods")
public class ExamPeriodController {

	@Autowired
	private ExamPeriodService examPeriodService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ExamPeriodDTO>> getAllExamPeriods() {
		List<ExamPeriod> examPeriods = examPeriodService.findAll();
		//convert examPeriods to DTOs
		List<ExamPeriodDTO> examPeriodsDTO = new ArrayList<>();
		for (ExamPeriod s : examPeriods) {
			examPeriodsDTO.add(new ExamPeriodDTO(s));
		}
		return new ResponseEntity<>(examPeriodsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExamPeriodDTO>> getExamPeriodsPage(Pageable page) {
		Page<ExamPeriod> examPeriods = examPeriodService.findAll(page);
		
		//convert examPeriods to DTOs
		List<ExamPeriodDTO> examPeriodsDTO = new ArrayList<>();
		for (ExamPeriod s : examPeriods) {
			examPeriodsDTO.add(new ExamPeriodDTO(s));
		}
		return new ResponseEntity<>(examPeriodsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "examPeriodDetails/{id}", method=RequestMethod.GET)
	public ResponseEntity<ExamPeriodDTO> getExamPeriod(@PathVariable Long id){
		ExamPeriod examPeriod = examPeriodService.findOne(id);
		if(examPeriod == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new ExamPeriodDTO(examPeriod), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ExamPeriodDTO> saveExamPeriod(@RequestBody ExamPeriodDTO examPeriodDTO){
		ExamPeriod examPeriod = new ExamPeriod();
		
		examPeriod.setName(examPeriodDTO.getName());
		examPeriod.setStartDate((Date) examPeriodDTO.getStartDate());
		examPeriod.setEndDate((Date) examPeriodDTO.getEndDate());
		
		examPeriod = examPeriodService.save(examPeriod);
		return new ResponseEntity<>(new ExamPeriodDTO(examPeriod), HttpStatus.CREATED);	
	}
	
	@RequestMapping(value = "updateExamPeriod/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ExamPeriodDTO> updateExamPeriod(@PathVariable("id") long id,
			@RequestBody ExamPeriodDTO examPeriodDTO){
		
		ExamPeriod examPeriod = examPeriodService.findOne(examPeriodDTO.getId()); 
		if (examPeriod == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		examPeriod.setName(examPeriodDTO.getName());
		examPeriod.setStartDate((Date) examPeriodDTO.getStartDate());
		examPeriod.setEndDate((Date) examPeriodDTO.getEndDate());
		
		examPeriod = examPeriodService.save(examPeriod);
		return new ResponseEntity<>(new ExamPeriodDTO(examPeriod), HttpStatus.OK);	
	}
	
	@RequestMapping(value = "deleteExamPeriod/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteExamPeriod(@PathVariable Long id){
		ExamPeriod examPeriod = examPeriodService.findOne(id);
		if (examPeriod != null){
			examPeriodService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{examPeriodId}/exams", method = RequestMethod.GET)
	public ResponseEntity<List<ExamDTO>> getExamPeriodExams(
			@PathVariable Long examPeriodId) {
		ExamPeriod examPeriod = examPeriodService.findOne(examPeriodId);
		Set<Exam> exams = examPeriod.getExams();
		List<ExamDTO> examsDTO = new ArrayList<>();
		for (Exam e: exams) {
			ExamDTO examDTO = new ExamDTO();
			examDTO.setId(e.getId());
			examDTO.setExamPoints(e.getExamPoints());
			examDTO.setLabPoints(e.getLabPoints());
			examDTO.setDatum(e.getDatum());
			examDTO.setCourse(new CourseDTO(e.getCourse()));
			examDTO.setStudent(new StudentDTO(e.getStudent()));
		
			examsDTO.add(examDTO);
		}
		return new ResponseEntity<>(examsDTO, HttpStatus.OK);
	}

}
