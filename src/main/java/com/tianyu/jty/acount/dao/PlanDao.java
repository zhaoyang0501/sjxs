package com.tianyu.jty.acount.dao;
import org.springframework.stereotype.Repository;

import com.tianyu.jty.acount.entity.Plan;
import com.tianyu.jty.common.persistence.HibernateDao;


/**
 * 用户DAO
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class PlanDao extends HibernateDao<Plan, Integer>{
}
