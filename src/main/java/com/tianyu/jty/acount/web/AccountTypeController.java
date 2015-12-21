package com.tianyu.jty.acount.web;

import java.util.List;

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

import com.tianyu.jty.acount.entity.AccountType;
import com.tianyu.jty.acount.service.AccountTypeService;
import com.tianyu.jty.common.web.BaseController;

/**
 * 商品类型类型controller
 * @author ty
 * @date 2015年1月22日
 */
@Controller
@RequestMapping("account/accountType")
public class AccountTypeController extends BaseController{

	@Autowired
	private AccountTypeService TypeService;

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "account/accountTypeList";
	}
	
	/**
	 * 获取商品类型类型json
	 */
	@RequestMapping(value="json",method = RequestMethod.GET)
	@ResponseBody
	public List<AccountType> TypeList(HttpServletRequest request) {
		List<AccountType> TypeList=TypeService.getAll();
		return TypeList;
	}
	
	/**
	 * 添加商品类型跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("Type", new AccountType());
		model.addAttribute("action", "create");
		return "account/accountTypeForm";
	}

	/**
	 * 添加商品类型
	 * 
	 * @param Type
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid AccountType Type, Model model) {
		TypeService.save(Type);
		return "success";
	}

	/**
	 * 修改商品类型跳转
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("accountType", TypeService.get(id));
		model.addAttribute("action", "update");
		return "account/accountTypeForm";
	}

	/**
	 * 修改商品类型
	 * 
	 * @param Type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute @RequestBody AccountType Type,Model model) {
		TypeService.update(Type);
		return "success";
	}

	/**
	 * 删除商品类型
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		TypeService.delete(id);
		return "success";
	}
	
	@ModelAttribute
	public void getAccountType(@RequestParam(value = "id", defaultValue = "-1") Integer id,Model model) {
		if (id != -1) {
			model.addAttribute("Type", TypeService.get(id));
		}
	}
}
