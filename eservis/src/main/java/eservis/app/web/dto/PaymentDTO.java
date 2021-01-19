package eservis.app.web.dto;

import java.sql.Date;

import eservis.app.model.Payment;

public class PaymentDTO {

	private Long id;
	private String svrha;
	private double amount;
	private Date datum;
	private StudentDTO student;
	
	public PaymentDTO() {
		
	}
	
	public PaymentDTO(Payment payment) {
		super();
		this.id = payment.getId();
		this.svrha = payment.getSvrha();
		this.amount = payment.getAmount();
		this.datum = payment.getDatum();
		this.student = new StudentDTO(payment.getStudent());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSvrha() {
		return svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
}