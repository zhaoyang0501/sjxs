package com.tianyu.jty.acount.dao;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.tianyu.jty.acount.entity.Trade;
import com.tianyu.jty.common.persistence.HibernateDao;


/**
 * 用户DAO
 * @author ty
 * @date 2015年1月13日
 */
@Repository
public class TradeDao extends HibernateDao<Trade, Integer>{
	@SuppressWarnings("unchecked")
	public List<Trade> findByCard(String cardid){
		String hql="select t from Trade t where t.fromCard.id=?0";
		Query query= createQuery(hql, cardid);
		return query.list();
	}
}
