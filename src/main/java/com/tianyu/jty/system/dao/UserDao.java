package com.tianyu.jty.system.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.system.entity.User;


/**
 * 用户DAO
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class UserDao extends HibernateDao<User, Integer>{
	@SuppressWarnings("unchecked")
	public List<User> findByPuser(Integer id){
		String hql="select u from User u where u.puser.id=?0";
		Query query= createQuery(hql, id);
		return  query.list();
		
	}
}
