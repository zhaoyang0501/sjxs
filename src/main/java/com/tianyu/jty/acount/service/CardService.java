package com.tianyu.jty.acount.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyu.jty.acount.dao.CardDao;
import com.tianyu.jty.acount.entity.AccountUser;
import com.tianyu.jty.acount.entity.Card;
import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;

/**
 * 商品类型service
 * @author ty
 * @date 2015年1月22日
 */
@Service
@Transactional(readOnly=true)
public class CardService extends BaseService<Card, String> {
	
	@Autowired
	private CardDao cardDao;

	@Override
	public HibernateDao<Card, String> getEntityDao() {
		return cardDao;
	}
	public List<Card> findByUser(AccountUser accountUser){
		return this.cardDao.findByUser(accountUser);
	}
	public List<Card> findByUserAndType(AccountUser accountUser,Integer typeid){
		return this.cardDao.findByUser(accountUser);
	}
	
}
