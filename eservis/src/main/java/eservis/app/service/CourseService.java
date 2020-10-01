package eservis.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eservis.app.model.Course;
import eservis.app.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CourseService {
	
	@Autowired
	CourseRepository courseRepository;
	
	public Course findOne(Long id) {
		return courseRepository.findById(id).orElse(null);
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
	}
	
	public Page<Course> findAll(Pageable page) {
		return courseRepository.findAll(page);
	}

	public Course save(Course course) {
		return courseRepository.save(course);
	}

	public void remove(Long id) {
		courseRepository.deleteById(id);
	}

}
