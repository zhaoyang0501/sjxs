package com.tianyu.jty.acount.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.acount.entity.AccountUser;


/**
 * 用户DAO
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class AccountUserDao extends HibernateDao<AccountUser, Integer>{
	@SuppressWarnings("unchecked")
	public AccountUser findUser(String loginName,String password){
		String hql="select t from AccountUser t where t.loginName=?0 and t.password=?1";
		Query query= createQuery(hql, loginName,password);
		List<AccountUser> list=query.list();
		return list.size()>=1?list.get(0):null;
	}
}
