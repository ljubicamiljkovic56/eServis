package eservis.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eservis.app.model.Payment;
import eservis.app.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public Payment findOne(Long id) {
		return paymentRepository.findById(id).orElse(null);
	}

	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}
	
	public Page<Payment> findAll(Pageable page) {
		return paymentRepository.findAll(page);
	}

	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}

	public void remove(Long id) {
		paymentRepository.deleteById(id);
	}

}
