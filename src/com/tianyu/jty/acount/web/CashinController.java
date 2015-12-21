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
 * 存款 controller
 */
@Controller
@RequestMapping("account/cashin")
public class CashinController extends BaseController {

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
		return "account/cashin";
	}
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String save( Trade trade, Model model) {
		trade.setCreateDate(new Date(System.currentTimeMillis()));
		trade.setType("存款");
		//给目标账户加钱
		
		Card card =cardService.get(trade.getFromCard().getId());
		card.setCash(card.getCash()+trade.getCash());
		cardService.save(card);
		trade.setRestCash(card.getCash());
		tradeService.save(trade);
		return "success";
	}

}
