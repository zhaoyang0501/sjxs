package com.tianyu.jty.acount.dto;

import java.util.List;

import com.tianyu.jty.acount.entity.Plan;

public class PlanForm {
	private Plan plan;
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public List<Plan> getPlans() {
		return plans;
	}
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}
	private List<Plan> plans;
}
