package eservis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
