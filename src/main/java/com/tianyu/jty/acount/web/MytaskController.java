package com.tianyu.jty.acount.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyu.jty.acount.entity.AccountUser;
import com.tianyu.jty.acount.entity.Sale;
import com.tianyu.jty.acount.entity.SaleType;
import com.tianyu.jty.acount.service.SaleService;
import com.tianyu.jty.acount.service.SaleTypeService;
import com.tianyu.jty.common.persistence.Page;
import com.tianyu.jty.common.persistence.PropertyFilter;
import com.tianyu.jty.common.web.BaseController;
import com.tianyu.jty.system.utils.UserUtil;

/**
 * 用户controller
 * @author ty
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("account/mytask")
public class MytaskController extends BaseController {

	@Autowired
	private SaleService saleService;
	@Autowired
	private SaleTypeService saleTypeService;
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "account/mytaskList";
	}
	@RequestMapping(value="getsaletype/{id}",method = RequestMethod.GET)
	@ResponseBody
	public List<SaleType> getsaletype(@PathVariable("id") Integer id,HttpServletRequest request) {
		return saleTypeService.findByCard(id);
	}
	/**
	 * 获取用户json
	 */
	@RequestMapping(value="json",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Page<Sale> page = getPage(request);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		filters.add(new PropertyFilter("EQI_userid",UserUtil.getCurrentUser().getId().toString()));
		page = saleService.search(page, filters);
		return getEasyUIData(page);
	}

	/**
	 * 添加用户跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("user", new AccountUser());
		model.addAttribute("action", "create");
		return "account/userForm";
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Sale sale, Model model) {
		saleService.save(sale);
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
	public String updateForm(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("sale", saleService.get(id));
		model.addAttribute("action", "update");
		return "account/mytaskdetail";
	}

	@ModelAttribute
	public void getSale(@RequestParam(value = "id", defaultValue = "-1") Integer id,Model model) {
		if (id != -1) {
			model.addAttribute("sale", saleService.get(id));
		}
	}

}
