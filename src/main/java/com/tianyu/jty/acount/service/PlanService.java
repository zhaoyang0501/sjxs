package com.tianyu.jty.acount.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyu.jty.acount.dao.PlanDao;
import com.tianyu.jty.acount.entity.Plan;
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
public class PlanService extends BaseService<Plan, Integer> {
	
	@Autowired
	private PlanDao planDao;

	@Override
	public HibernateDao<Plan, Integer> getEntityDao() {
		return planDao;
	}

	/**
	 * 保存用户
	 * @param user
	 */
	@Transactional(readOnly=false)
	public void save(Plan user) {
		user.setCreateDate(DateUtils.getSysTimestamp());
		planDao.save(user);
	}

	
	/**
	 * 删除用户
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void delete(Integer id){
			planDao.delete(id);
	}
	public List<Plan> findByPid(Integer pid){
		return this.planDao.findByPid(pid);
	}
	public List<Plan> findByPid(Integer uid,Date createDat){
		return this.planDao.findByUser(uid, createDat);
	}
}
