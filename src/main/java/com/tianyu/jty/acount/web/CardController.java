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

import com.tianyu.jty.acount.entity.AccountUser;
import com.tianyu.jty.acount.entity.Card;
import com.tianyu.jty.acount.service.AccountTypeService;
import com.tianyu.jty.acount.service.AccountUserService;
import com.tianyu.jty.acount.service.CardService;
import com.tianyu.jty.acount.util.CardIDUtil;
import com.tianyu.jty.common.persistence.Page;
import com.tianyu.jty.common.persistence.PropertyFilter;
import com.tianyu.jty.common.web.BaseController;

/**
 * 用户controller
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
	 * 获取用户json
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
	 * 添加用户跳转
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
	 * 添加用户
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
	 * 修改用户跳转
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
	 * 修改用户
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
	 * 冻结用户
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

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	
	
}
