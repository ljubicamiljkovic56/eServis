package eservis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
