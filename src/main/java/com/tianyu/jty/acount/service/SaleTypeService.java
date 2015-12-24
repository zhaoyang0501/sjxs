package com.tianyu.jty.acount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyu.jty.acount.dao.SaleTypeDao;
import com.tianyu.jty.acount.entity.SaleType;
import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;

/**
 * 商品类型service
 * @author ty
 * @date 2015年1月22日
 */
@Service
@Transactional(readOnly=true)
public class SaleTypeService extends BaseService<SaleType, Integer> {
	
	@Autowired
	private SaleTypeDao saleTypeDao;
	public List<SaleType> findByCard(Integer tid){
		return this.saleTypeDao.findByType(tid);
	}
	@Override
	public HibernateDao<SaleType, Integer> getEntityDao() {
		return saleTypeDao;
	}

}
