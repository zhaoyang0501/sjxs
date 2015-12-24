package com.tianyu.jty.acount.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tianyu.jty.acount.entity.SaleType;
import com.tianyu.jty.common.persistence.HibernateDao;

/**
 * 
 * @date 2015年1月22日
 */
@Repository
public class SaleTypeDao extends HibernateDao<SaleType, Integer>{
	@SuppressWarnings("unchecked")
	public List<SaleType> findByType(Integer tid){
		String hql="select t from SaleType t where t.accountType.id=?0";
		Query query= createQuery(hql, tid);
		return query.list();
	}
}
