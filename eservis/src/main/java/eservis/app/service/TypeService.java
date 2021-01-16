package eservis.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eservis.app.model.Type;
import eservis.app.repository.TypeRepository;

@Service
public class TypeService {
	
	@Autowired
	TypeRepository typeRepository;

	public Type findOne(Long id) {
		return typeRepository.findById(id).orElse(null);
	}

	public List<Type> findAll() {
		return typeRepository.findAll();
	}
	
	public Page<Type> findAll(Pageable page) {
		return typeRepository.findAll(page);
	}

	public Type save(Type type) {
		return typeRepository.save(type);
	}

	public void remove(Long id) {
		typeRepository.deleteById(id);
	}
}
