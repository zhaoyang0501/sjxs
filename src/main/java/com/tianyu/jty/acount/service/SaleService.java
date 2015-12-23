package com.tianyu.jty.acount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyu.jty.acount.dao.SaleDao;
import com.tianyu.jty.acount.entity.Sale;
import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;
import com.tianyu.jty.common.utils.DateUtils;

/**
 * 用户service
 * @author ty
 * @date 2015年1月13日
 */
@Service
@Transactional(readOnly = true)
public class SaleService extends BaseService<Sale, Integer> {
	
	@Autowired
	private SaleDao userDao;

	@Override
	public HibernateDao<Sale, Integer> getEntityDao() {
		return userDao;
	}

	/**
	 * 保存用户
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void save(Sale user) {
		user.setCreateDate(DateUtils.getSysTimestamp());
		userDao.save(user);
	}

	
	/**
	 * 删除用户
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void delete(Integer id){
			userDao.delete(id);
	}
}
