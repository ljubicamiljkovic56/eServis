package eservis.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eservis.app.model.Authority;
import eservis.app.repository.AuthorityRepository;

@Service
public class AuthorityService {
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	public Authority findOne(Long id) {
		return authorityRepository.findById(id).orElse(null);
	}

}
