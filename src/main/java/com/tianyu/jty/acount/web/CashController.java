package com.tianyu.jty.acount.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyu.jty.acount.service.SaleService;
import com.tianyu.jty.common.web.BaseController;
import com.tianyu.jty.system.service.UserService;
import com.tianyu.jty.system.utils.UserUtil;

/**
 * 用户controller
 * @author ty
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("account/cash")
public class CashController extends BaseController {

	@Autowired
	private SaleService saleService;
	@Autowired
	private UserService userService;
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("cash", userService.get(UserUtil.getCurrentUser().getId()).getCash());
		return "account/cash";
	}
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param model
	 */
	@RequestMapping( method = RequestMethod.POST)
	@ResponseBody
	public String create(Integer num, Model model) {
		
		userService.updatecash(0.0+num,UserUtil.getCurrentUser().getId());
		return "success";
	}

}
