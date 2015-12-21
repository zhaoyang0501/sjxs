package com.tianyu.jty.acount.web;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyu.jty.acount.entity.Card;
import com.tianyu.jty.acount.entity.Trade;
import com.tianyu.jty.acount.service.AccountTypeService;
import com.tianyu.jty.acount.service.AccountUserService;
import com.tianyu.jty.acount.service.CardService;
import com.tianyu.jty.acount.service.TradeService;
import com.tianyu.jty.common.persistence.Page;
import com.tianyu.jty.common.persistence.PropertyFilter;
import com.tianyu.jty.common.web.BaseController;

/**
 * 用户controller
 * @author ty
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("account/cashout")
public class CashoutController extends BaseController {

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
		return "account/cashout";
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String save( Trade trade, Model model) {
		//给目标账户加钱
		Card card =cardService.get(trade.getFromCard().getId());
		if(card.getCash()<trade.getCash())
			return "余额不足";
		if(card.getAccountType().getId()==3||card.getAccountType().getId()==4){
			return "此账户属于"+card.getAccountType().getName()+",权限不足！";
		}
		
		card.setCash(card.getCash()-trade.getCash());
		cardService.save(card);
		
		trade.setCreateDate(new Date(System.currentTimeMillis()));
		trade.setType("取款");
		trade.setCash(-trade.getCash());
		trade.setRestCash(card.getCash());
		tradeService.save(trade);
		return "success";
	}
	@ResponseBody
	@RequestMapping(value="getCardCash")
	public Double getCardCash( String cardid) {
		return cardService.get(cardid).getCash();
		
	}
}
