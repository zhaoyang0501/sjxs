package com.tianyu.jty.acount.web;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyu.jty.acount.entity.Card;
import com.tianyu.jty.acount.service.AccountTypeService;
import com.tianyu.jty.acount.service.AccountUserService;
import com.tianyu.jty.acount.service.CardService;
import com.tianyu.jty.acount.util.CardIDUtil;
import com.tianyu.jty.common.persistence.Page;
import com.tianyu.jty.common.persistence.PropertyFilter;
import com.tianyu.jty.common.web.BaseController;

/**
 * 账户管理  开户销户等 controller
 * @author ty
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("account/card")
public class CardController extends BaseController {

	@Autowired
	private CardService cardService;
	
	@Autowired
	private AccountUserService accountUserService;
	
	@Autowired
	private AccountTypeService accountTypeService;
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list( Model model) {
		model.addAttribute("accountTypes",accountTypeService.getAll() );
		return "account/cardList";
	}

	/**
	 * 获取账户json
	 */
	@RequestMapping(value="json",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Page<Card> page = getPage(request);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		page = cardService.search(page, filters);
		return getEasyUIData(page);
	}

	/**
	 * 添加账户跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("card", new Card());
		model.addAttribute("accountTypes",accountTypeService.getAll() );
		model.addAttribute("accountUsers",accountUserService.getAll() );
		model.addAttribute("action", "create");
		return "account/cardForm";
	}

	/**
	 * 添加账户，检查基本类型唯一
	 * 
	 * @param card
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Card card, Model model) {
		card.setId(CardIDUtil.getID(card.getAccountType().getId()));
		card.setCreateDate(new Date(System.currentTimeMillis()));
		card.setState("正常");
		/*检查基本卡**/
		if(card.getAccountType().getId()==1){
			List<Card> cards=cardService.findByUserAndType(card.getAccountUser(),1);
			if(cards.size()>0)
				return "基本类型的卡一个用户只能开一张！";
		}
		cardService.save(card);
		return "success";
	}

	/**
	 * 修改账户跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") String id, Model model) {
		model.addAttribute("card", cardService.get(id));
		model.addAttribute("accountTypes",accountTypeService.getAll() );
		model.addAttribute("accountUsers",accountUserService.getAll() );
		model.addAttribute("action", "update");
		return "account/cardForm";
	}

	/**
	 * 修改账户
	 * 
	 * @param card
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(  Card card) {
		Card oldCard=cardService.get(card.getId());
		oldCard.setAccountType(card.getAccountType());
		oldCard.setAccountUser(card.getAccountUser());
		oldCard.setBank(card.getBank());
		oldCard.setPersonno(card.getPersonno());
		oldCard.setCash(card.getCash());
		
		/*检查基本卡**/
		if(oldCard.getAccountType().getId()==1){
			List<Card> cards=cardService.findByUserAndType(oldCard.getAccountUser(),1);
			if(cards.size()>1)
				return "基本类型的卡一个用户只能开一张！";
		}
		
		cardService.update(oldCard);
		return "success";
	}

	/**
	 * 销户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") String id) {
		Card card=cardService.get(id);
		card.setState("销户");
		cardService.update(card);
		return "success";
	}
}
