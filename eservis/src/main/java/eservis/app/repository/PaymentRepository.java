package eservis.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eservis.app.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
