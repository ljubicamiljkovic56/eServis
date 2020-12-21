package eservis.app.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eservis.app.model.Document;
import eservis.app.service.DocumentService;
import eservis.app.web.dto.DocumentDTO;

@RestController
@RequestMapping(value="api/documents")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DocumentDTO>> getDocuments(){
		List<Document> documents = documentService.findAll();
		
		List<DocumentDTO> documentsDTO = new ArrayList<DocumentDTO>();
		for(Document doc : documents) {
			documentsDTO.add(new DocumentDTO(doc));
		}
		
		return new ResponseEntity<List<DocumentDTO>>(documentsDTO, HttpStatus.OK);
	}

}
