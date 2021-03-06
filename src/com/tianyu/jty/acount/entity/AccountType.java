package com.tianyu.jty.acount.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 账户类型 一般账户等
 */
@Entity
@Table(name = "account_type")
public class AccountType implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	/*类型名称**/
	private String name;
	/*备注**/
	private String remark;

	// Constructors

	/** default constructor */
	public AccountType() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	@Column(name = "NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String img) {
		this.remark = img;
	}

}