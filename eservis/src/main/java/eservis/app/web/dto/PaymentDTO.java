package eservis.app.web.dto;

import java.util.Date;

import eservis.app.model.Payment;

public class PaymentDTO {
	
private Long id;
	
	private StudentDTO student;

	private String svrha;
	
	private String primalac;
	
	private double amount;
	
	private String racun;
	
	private int model;
	
	private String pozivNaBroj;
	
	private Date datum;
	
	public PaymentDTO() {
		
	}

	public PaymentDTO(Long id, StudentDTO student, String svrha, String primalac, double amount, String racun,
			int model, String pozivNaBroj, Date datum) {
		super();
		this.id = id;
		this.student = student;
		this.svrha = svrha;
		this.primalac = primalac;
		this.amount = amount;
		this.racun = racun;
		this.model = model;
		this.pozivNaBroj = pozivNaBroj;
		this.datum = datum;
	}

	public PaymentDTO(Payment payment) {
		id = payment.getId();
		student = new StudentDTO(payment.getStudent());
		svrha = payment.getSvrha();
		primalac = payment.getPrimalac();
		amount = payment.getAmount();
		racun = payment.getRacun();
		model = payment.getModel();
		pozivNaBroj = payment.getPozivNaBroj();
		datum = payment.getDatum();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentDTO getStudent() {
		return student;
	}

	public void setStudent(StudentDTO student) {
		this.student = student;
	}

	public String getSvrha() {
		return svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}

	public String getPrimalac() {
		return primalac;
	}

	public void setPrimalac(String primalac) {
		this.primalac = primalac;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getRacun() {
		return racun;
	}

	public void setRacun(String racun) {
		this.racun = racun;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public String getPozivNaBroj() {
		return pozivNaBroj;
	}

	public void setPozivNaBroj(String pozivNaBroj) {
		this.pozivNaBroj = pozivNaBroj;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
}