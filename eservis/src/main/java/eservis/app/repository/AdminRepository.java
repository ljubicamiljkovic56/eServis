package eservis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
