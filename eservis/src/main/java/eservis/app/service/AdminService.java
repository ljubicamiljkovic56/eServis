package eservis.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eservis.app.model.Admin;

import eservis.app.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	public Admin findOne(Long id) {
		return adminRepository.findById(id).orElse(null);
	}
	
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}
	
	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}

	public void remove(Long id) {
		adminRepository.deleteById(id);
	}

}
