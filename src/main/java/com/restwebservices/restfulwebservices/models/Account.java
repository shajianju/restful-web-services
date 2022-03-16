package com.restwebservices.restfulwebservices.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author anjus
 *
 */
@Entity
@Table(name ="account")
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(initialValue = 2540,allocationSize = 25,name = "acc_seq")
	private long id;

	private String name;

	@OneToMany(fetch = FetchType.LAZY,	cascade = CascadeType.ALL,targetEntity = Employee.class)
	@JoinColumn(name = "join_pk",referencedColumnName = "id")
	private List<Employee> empList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public Account(long id, String name, List<Employee> empList) {
		super();
		this.id = id;
		this.name = name;
		this.empList = empList;
	}

	public Account(String name) {
		super();
		this.name = name;
	}

	public Account() {
		super();
	}





}
