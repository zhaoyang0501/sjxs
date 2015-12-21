package com.tianyu.jty.acount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyu.jty.acount.dao.AccountUserDao;
import com.tianyu.jty.acount.entity.AccountUser;
import com.tianyu.jty.common.persistence.HibernateDao;
import com.tianyu.jty.common.service.BaseService;
import com.tianyu.jty.common.utils.DateUtils;

/**
 * 用户 service
 */
@Service
@Transactional(readOnly = true)
public class AccountUserService extends BaseService<AccountUser, Integer> {
	
	@Autowired
	private AccountUserDao userDao;

	@Override
	public HibernateDao<AccountUser, Integer> getEntityDao() {
		return userDao;
	}

	/**
	 * 保存用户
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void save(AccountUser user) {
		user.setCreateDate(DateUtils.getSysTimestamp());
		userDao.save(user);
	}

	/**
	 * 修改密码
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void updatePwd(AccountUser user) {
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
	
	/**
	 * 按登录名查询用户
	 * @param loginName
	 * @return 用户对象
	 */
	public AccountUser getUser(String loginName) {
		return userDao.findUniqueBy("loginName", loginName);
	}
	public AccountUser getUser(String loginName,String password) {
		return userDao.findUser(loginName, password);
	}
}
