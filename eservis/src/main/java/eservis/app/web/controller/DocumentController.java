package eservis.app.web.controller;

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

import eservis.app.model.Document;
import eservis.app.model.Student;
import eservis.app.model.Type;
import eservis.app.service.DocumentService;
import eservis.app.service.StudentService;
import eservis.app.service.TypeService;
import eservis.app.web.dto.DocumentDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="api/documents")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TypeService typeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DocumentDTO>> getDocuments(){
		List<Document> documents = documentService.findAll();
		
		List<DocumentDTO> documentsDTO = new ArrayList<DocumentDTO>();
		for(Document doc : documents) {
			documentsDTO.add(new DocumentDTO(doc));
		}
		
		return new ResponseEntity<List<DocumentDTO>>(documentsDTO, HttpStatus.OK);
	}
	
	//po id-u
		@RequestMapping(value = "documentDetails/{id}", method = RequestMethod.GET)
		public ResponseEntity<DocumentDTO> getDocument(@PathVariable Long id){
			Document document = documentService.findOne(id);
			if(document == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(new DocumentDTO(document), HttpStatus.OK);
		}
		
		//novi dokument
		@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
		public ResponseEntity<DocumentDTO> saveDocument(@RequestBody DocumentDTO documentDTO){
			if(documentDTO.getType() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			if (documentDTO.getStudent() == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}		
			
			Student student = studentService.findOne(documentDTO.getStudent().getId());
			
			Type type = typeService.findOne(documentDTO.getType().getId());
			
			Document document = new Document();
			document.setType(type);
			document.setStudent(student);
			document = documentService.save(document);
			return new ResponseEntity<>(new DocumentDTO(document), HttpStatus.CREATED);	
		}
		
		//izmeni dokument
		@RequestMapping(value = "updateDocument/{id}", method = RequestMethod.PUT, consumes = "application/json")
		public ResponseEntity<DocumentDTO> updateCourse(@PathVariable("id") long id, @RequestBody DocumentDTO documentDTO){
			Document document = documentService.findOne(documentDTO.getId()); 
			if (document == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Student student =  studentService.findOne(documentDTO.getStudent().getId());
			
			Type type = typeService.findOne(documentDTO.getType().getId());
			
			document.setType(type);
			document.setStudent(student);
			document = documentService.save(document);
			return new ResponseEntity<>(new DocumentDTO(document), HttpStatus.OK);	
		}
		
		
		//obrisi dokument
		@RequestMapping(value = "deleteDocument/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> deleteDocument(@PathVariable Long id){
			Document document = documentService.findOne(id);
			if (document != null){
				documentService.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {		
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

}
