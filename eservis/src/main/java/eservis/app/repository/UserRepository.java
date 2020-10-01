package eservis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
