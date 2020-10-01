package eservis.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Lecture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToMany
    @JoinTable(name = "teaching",
               joinColumns = @JoinColumn(name="course_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="teacher_id", referencedColumnName="id"))
	private Set<Teacher> teachers = new HashSet<Teacher>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Set<Teacher> getTeachers() {
		return teachers;
	}


	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}

}