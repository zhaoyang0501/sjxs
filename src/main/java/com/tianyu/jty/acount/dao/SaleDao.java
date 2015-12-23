package com.tianyu.jty.acount.dao;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.tianyu.jty.acount.entity.Sale;
import com.tianyu.jty.acount.entity.Trade;
import com.tianyu.jty.common.persistence.HibernateDao;


/**
 * 用户DAO
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class SaleDao extends HibernateDao<Sale, Integer>{
}
