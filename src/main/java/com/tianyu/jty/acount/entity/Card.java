package com.tianyu.jty.acount.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@Entity
@Table(name = "acount_card")
@DynamicUpdate @DynamicInsert
public class Card implements java.io.Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
@Id
private String id;
/**身份证号码*/
private String personno;
private String bank;
private Date createDate;
private Double cash;
@ManyToOne(fetch = FetchType.EAGER)
private AccountUser accountUser;
@ManyToOne(fetch = FetchType.EAGER)
private AccountType accountType;
private String password;
/***
 * 正常，冻结
 */
private String  state;
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getPersonno() {
	return personno;
}
public void setPersonno(String personno) {
	this.personno = personno;
}
public String getBank() {
	return bank;
}
public void setBank(String bank) {
	this.bank = bank;
}
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public Double getCash() {
	return cash;
}
public void setCash(Double cash) {
	this.cash = cash;
}
public AccountUser getAccountUser() {
	return accountUser;
}
public void setAccountUser(AccountUser accountUser) {
	this.accountUser = accountUser;
}
public AccountType getAccountType() {
	return accountType;
}
public void setAccountType(AccountType accountType) {
	this.accountType = accountType;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
}
