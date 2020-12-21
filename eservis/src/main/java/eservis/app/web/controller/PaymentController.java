package eservis.app.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eservis.app.model.Payment;
import eservis.app.service.PaymentService;
import eservis.app.web.dto.PaymentDTO;

@RestController
@RequestMapping(value="api/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PaymentDTO>> getPayments(){
		List<Payment> payments = paymentService.findAll();
		
		List<PaymentDTO> paymentsDTO = new ArrayList<PaymentDTO>();
		for(Payment p : payments) {
			paymentsDTO.add(new PaymentDTO(p));
		}
		return new ResponseEntity<List<PaymentDTO>>(paymentsDTO, HttpStatus.OK);
	}

}
