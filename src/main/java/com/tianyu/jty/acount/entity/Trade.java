package com.tianyu.jty.acount.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "acount_trade")
@DynamicUpdate @DynamicInsert
public class Trade {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Card fromCard;
	@ManyToOne(fetch = FetchType.EAGER)
	private Card toCard;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createDate;
	private String remark;
	private Double cash;
	private Double restCash;
	
	private String man;
	/**转账，存款，取款*/
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Card getFromCard() {
		return fromCard;
	}
	public void setFromCard(Card fromCard) {
		this.fromCard = fromCard;
	}
	public Card getToCard() {
		return toCard;
	}
	public void setToCard(Card toCard) {
		this.toCard = toCard;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Double getRestCash() {
		return restCash;
	}
	public void setRestCash(Double restCash) {
		this.restCash = restCash;
	}
	public String getMan() {
		return man;
	}
	public void setMan(String man) {
		this.man = man;
	}
}
