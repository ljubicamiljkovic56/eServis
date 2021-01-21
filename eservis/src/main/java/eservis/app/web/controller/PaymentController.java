package eservis.app.web.controller;

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

import eservis.app.model.Payment;
import eservis.app.model.Student;
import eservis.app.service.PaymentService;
import eservis.app.service.StudentService;
import eservis.app.web.dto.PaymentDTO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "api/payments")
public class PaymentController {
	
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired StudentService studentService;
	
	//sve uplate
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PaymentDTO>> getPaymentsPage(Pageable page) {
		Page<Payment> payments =  paymentService.findAll(page);
		
		List<PaymentDTO> paymentsDTO = new ArrayList<>();
		for (Payment p : payments) {
			paymentsDTO.add(new PaymentDTO(p));
		}
		return new ResponseEntity<>(paymentsDTO, HttpStatus.OK);
	}
	
	//po id-u
	@RequestMapping(value = "paymentDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id){
		Payment payment = paymentService.findOne(id);
		if(payment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new PaymentDTO(payment), HttpStatus.OK);
	}
	
	//nova uplata
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<PaymentDTO> savePayment(@RequestBody PaymentDTO paymentDTO){
		if (paymentDTO.getStudent() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		Student student =  studentService.findOne(paymentDTO.getStudent().getId());
		
		Payment payment = new Payment();
		payment.setSvrha(paymentDTO.getSvrha());
		payment.setAmount(paymentDTO.getAmount());
		payment.setDatum(paymentDTO.getDatum());
		payment.setStudent(student);
		payment = paymentService.save(payment);
		return new ResponseEntity<>(new PaymentDTO(payment), HttpStatus.CREATED);	
	}
	
	//izmeni uplatu
	@RequestMapping(value = "updatePayment/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<PaymentDTO> updatePayment(@PathVariable("id") long id, 
			@RequestBody PaymentDTO paymentDTO){
		Payment payment = paymentService.findOne(paymentDTO.getId()); 
		if (payment == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Student student =  studentService.findOne(paymentDTO.getStudent().getId());
		
		payment.setSvrha(paymentDTO.getSvrha());
		payment.setAmount(paymentDTO.getAmount());
		payment.setDatum(paymentDTO.getDatum());
		payment.setStudent(student);
		payment = paymentService.save(payment);
		return new ResponseEntity<>(new PaymentDTO(payment), HttpStatus.OK);	
	}
	
	
	//obrisi uplatu
	@RequestMapping(value = "deletePayment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePayment(@PathVariable Long id){
		Payment payment = paymentService.findOne(id);
		if (payment != null){
			paymentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	

}
