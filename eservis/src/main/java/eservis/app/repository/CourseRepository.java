package eservis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
