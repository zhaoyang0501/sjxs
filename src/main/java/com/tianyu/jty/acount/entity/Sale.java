package com.tianyu.jty.acount.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tianyu.jty.system.entity.User;
@Entity
@Table(name = "acount_sale")
@DynamicUpdate @DynamicInsert
public class Sale {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createDate;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	private String name;
	private String customer;
	private String addr;
	
	private Double cash;
	private Double earlycash;
	private Double realcash;
	private Integer totalnum;
	
	private Integer userid;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getTotalnum() {
		return totalnum;
	}
	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}
	@OneToMany( mappedBy = "sale",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<SaleItem> saleitems;
	
	public List<SaleItem> getSaleitems() {
		return saleitems;
	}
	public void setSaleitems(List<SaleItem> saleitems) {
		this.saleitems = saleitems;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Double getEarlycash() {
		return earlycash;
	}
	public void setEarlycash(Double earlycash) {
		this.earlycash = earlycash;
	}
	public Double getRealcash() {
		return realcash;
	}
	public void setRealcash(Double realcash) {
		this.realcash = realcash;
	}
	
}
