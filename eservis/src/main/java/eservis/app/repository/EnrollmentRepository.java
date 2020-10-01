package eservis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
