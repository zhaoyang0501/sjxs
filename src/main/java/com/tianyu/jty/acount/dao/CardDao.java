package com.tianyu.jty.acount.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tianyu.jty.acount.entity.AccountUser;
import com.tianyu.jty.acount.entity.Card;
import com.tianyu.jty.common.persistence.HibernateDao;


/**
 * 用户DAO
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class CardDao extends HibernateDao<Card, String>{
	@SuppressWarnings("unchecked")
	public List<Card> findByUser(AccountUser accountUser){
		String hql="select t from Card t where t.accountUser.id=?0";
		Query query= createQuery(hql, accountUser.getId());
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Card> findByUserAndType(AccountUser accountUser,Integer typeid){
		String hql="select t from Card t where t.accountUser.id=?0 and accountType.id=?1";
		Query query= createQuery(hql, accountUser.getId(),typeid);
		return query.list();
	}
}
