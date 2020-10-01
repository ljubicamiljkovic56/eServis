package eservis.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Student findOneByCardNumber(String cardNumber);
    List<Student> findAllByLastName(String lastName);

}
