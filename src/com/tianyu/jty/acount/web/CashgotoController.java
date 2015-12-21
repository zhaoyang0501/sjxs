package com.tianyu.jty.acount.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyu.jty.acount.entity.Card;
import com.tianyu.jty.acount.entity.Trade;
import com.tianyu.jty.acount.service.AccountTypeService;
import com.tianyu.jty.acount.service.AccountUserService;
import com.tianyu.jty.acount.service.CardService;
import com.tianyu.jty.acount.service.TradeService;
import com.tianyu.jty.common.web.BaseController;

/**
 * 转账  controller
 */
@Controller
@RequestMapping("account/cashgoto")
public class CashgotoController extends BaseController {

	@Autowired
	private CardService cardService;
	@Autowired
	private AccountTypeService accountTypeService;
	@Autowired
	private AccountUserService accountUserService;
	@Autowired
	private TradeService tradeService;
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list( Model model) {
		model.addAttribute("accountUsers",accountUserService.getAll() );
		model.addAttribute("accountTypes",accountTypeService.getAll() );
		model.addAttribute("cards",cardService.getAll() );
		return "account/cashgoto";
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String save( Trade trade, Model model) {
		//给目标账户加钱
		Card formcard =cardService.get(trade.getFromCard().getId());
		Card tocard =cardService.get(trade.getToCard().getId());
		if(formcard.getCash()<trade.getCash())
			return "余额不足";
		if(formcard.getAccountType().getId()==3||formcard.getAccountType().getId()==4){
			return "此账户属于"+formcard.getAccountType().getName()+",权限不足！";
		}
		
		formcard.setCash(formcard.getCash()-trade.getCash());
		cardService.save(formcard);
		
		tocard.setCash(tocard.getCash()+trade.getCash());
		cardService.save(tocard);
		
		trade.setCreateDate(new Date(System.currentTimeMillis()));
		trade.setType("转账");
		trade.setCash(-trade.getCash());
		trade.setRestCash(formcard.getCash());
		tradeService.save(trade);
		return "success";
	}
	
	/***
	 * 获取账户余额，防止透支
	 * @param cardid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getCardCash")
	public Double getCardCash( String cardid) {
		return cardService.get(cardid).getCash();
		
	}
}
