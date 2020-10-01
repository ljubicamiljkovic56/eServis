package eservis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
