package com.tianyu.jty.acount.dao;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tianyu.jty.acount.entity.Plan;
import com.tianyu.jty.common.persistence.HibernateDao;


/**
 * 用户DAO
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class PlanDao extends HibernateDao<Plan, Integer>{
	@SuppressWarnings("unchecked")
	public List<Plan> findByPid(Integer pid){
		String hql="select t from Plan t where t.pplan=?0";
		Query query= createQuery(hql, pid);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Plan> findByUser(Integer uid,Date createDate){
		String hql="select t from Plan t where t.user.id=?0 and t.startDate<=?1 and t.endDate>=?1";
		Query query= createQuery(hql, uid,createDate);
		return query.list();
	}
}
