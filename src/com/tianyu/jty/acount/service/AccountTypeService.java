package com.tianyu.jty.acount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyu.jty.acount.dao.AccountTypeDao;
import com.tianyu.jty.acount.entity.AccountType;
import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;

/**
 * 账户类型service
 */
@Service
@Transactional(readOnly=true)
public class AccountTypeService extends BaseService<AccountType, Integer> {
	
	@Autowired
	private AccountTypeDao accountTypeDao;

	@Override
	public HibernateDao<AccountType, Integer> getEntityDao() {
		return accountTypeDao;
	}

}
