package com.tianyu.jty.acount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyu.jty.acount.dao.TradeDao;
import com.tianyu.jty.acount.entity.Trade;
import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;
import com.tianyu.jty.common.utils.DateUtils;

/**
 * 交易记录service
 */
@Service
@Transactional(readOnly = true)
public class TradeService extends BaseService<Trade, Integer> {
	
	@Autowired
	private TradeDao userDao;

	@Override
	public HibernateDao<Trade, Integer> getEntityDao() {
		return userDao;
	}

	/**
	 * 保存用户
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void save(Trade user) {
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
	
	public List<Trade> findByCard(String cardid){
		return this.userDao.findByCard(cardid);
	}
}
