package eservis.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import eservis.app.model.Enrollment;
import eservis.app.repository.EnrollmentRepository;

@Service
public class EnrollmentService {
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	public Enrollment findOne(Long id) {
		return enrollmentRepository.findById(id).orElse(null);
	}

	public List<Enrollment> findAll() {
		return enrollmentRepository.findAll();
	}
	
	public Page<Enrollment> findAll(Pageable page) {
		return enrollmentRepository.findAll(page);
	}
	
	public Enrollment save(Enrollment enrollment) {
		return enrollmentRepository.save(enrollment);
	}

	public void remove(Long id) {
		enrollmentRepository.deleteById(id);
	}

}