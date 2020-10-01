package eservis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}
