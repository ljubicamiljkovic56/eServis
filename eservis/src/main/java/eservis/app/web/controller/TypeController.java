package eservis.app.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eservis.app.model.Type;
import eservis.app.service.TypeService;
import eservis.app.web.dto.TypeDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/types")
public class TypeController {
	
	@Autowired
	private TypeService typeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TypeDTO>> getTypes() {
		List<Type> types = typeService.findAll();
		//convert courses to DTOs
		List<TypeDTO> typesDTO = new ArrayList<TypeDTO>();
		for (Type t : types) {
			typesDTO.add(new TypeDTO(t));
		}
		return new ResponseEntity<>(typesDTO, HttpStatus.OK);
	}
	
	//po id-u
	@RequestMapping(value = "typeDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<TypeDTO> getType(@PathVariable Long id){
		Type type = typeService.findOne(id);
		if(type == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new TypeDTO(type), HttpStatus.OK);
	}

}
