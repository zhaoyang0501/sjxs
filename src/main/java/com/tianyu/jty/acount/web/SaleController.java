package com.tianyu.jty.acount.web;

import java.util.Date;
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

import com.tianyu.jty.acount.dto.PlanForm;
import com.tianyu.jty.acount.entity.Plan;
import com.tianyu.jty.acount.entity.Sale;
import com.tianyu.jty.acount.entity.SaleItem;
import com.tianyu.jty.acount.service.AccountTypeService;
import com.tianyu.jty.acount.service.PlanService;
import com.tianyu.jty.acount.service.SaleService;
import com.tianyu.jty.common.persistence.Page;
import com.tianyu.jty.common.persistence.PropertyFilter;
import com.tianyu.jty.common.web.BaseController;
import com.tianyu.jty.system.service.UserService;
import com.tianyu.jty.system.utils.UserUtil;

/**
 * 用户controller
 * @author ty
 * @date 2015年1月13日
 */
@Controller
@RequestMapping("account/sale")
public class SaleController extends BaseController {

	@Autowired
	private PlanService planService;
	@Autowired
	private UserService userService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private AccountTypeService accountTypeService;
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "account/planList";
	}

	/**
	 * 获取用户json
	 */
	@RequestMapping(value="json",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getData(HttpServletRequest request) {
		Page<Plan> page = getPage(request);
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
		page = planService.search(page, filters);
		return getEasyUIData(page);
	}
	@RequestMapping(value = "dist/{id}", method = RequestMethod.GET)
	public String dist(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("plan", planService.get(id));
		model.addAttribute("action", "update");
		model.addAttribute("users", userService.findByPuser(UserUtil.getCurrentUser().getId()));
		return "account/plandist";
	}
	
	@RequestMapping(value = "dist", method = RequestMethod.POST)
	@ResponseBody
	public String dist( PlanForm planform,Model model) {
		System.out.println(planform);
		
		Plan rooplan=planService.get(planform.getPlan().getId());
		for(Plan plan:planform.getPlans()){
			plan.setCreateDate(new Date());
			plan.setEndDate(rooplan.getEndDate());
			plan.setName(rooplan.getName());
			plan.setNum(plan.getNum());
			plan.setRemark(rooplan.getRemark());
			plan.setStartDate(rooplan.getStartDate());
			plan.setUser(userService.get(plan.getUser().getId()));
			plan.setPplan(plan.getId());
			planService.save(plan);
		}
		
		return "success";
	}
	/**
	 * 添加用户跳转
	 * 
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("plan", new Plan());
		model.addAttribute("action", "create");
		model.addAttribute("cash", userService.get(UserUtil.getCurrentUser().getId()).getCash());
		model.addAttribute("types",accountTypeService.getAll() );
		return "account/salecreate";
	}

	/**
	 * 添加用户
	 * 
	 * @param plan
	 * @param model
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public String create(@Valid Sale sale, Model model) {
		int a=0;
		for(SaleItem bean:sale.getSaleitems()){
			a+=bean.getNum();
			bean.setSale(sale);
		}
		sale.setTotalnum(a);
		sale.setUserid(UserUtil.getCurrentUser().getId());
		sale.setUser(userService.get(UserUtil.getCurrentUser().getId()));
		
		saleService.save(sale);
		
		List<Plan> plans=planService.findByPid(UserUtil.getCurrentUser().getId(), sale.getCreateDate());
		for(Plan plan:plans){
			plan.setEndnum(plan.getEndnum()+sale.getTotalnum());
			planService.save(plan);
			Plan plan1=planService.get(plan.getPplan());
			if(plan1!=null){
				plan1.setEndnum(plan1.getEndnum()+sale.getTotalnum());
				planService.save(plan1);
				Plan plan2=planService.get(plan1.getPplan());
				if(plan2!=null){
					plan2.setEndnum(plan2.getEndnum()+sale.getTotalnum());
					planService.save(plan2);
				}
			}
		}
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
		model.addAttribute("plan", planService.get(id));
		model.addAttribute("action", "update");
		return "account/planForm";
	}

	/**
	 * 修改用户
	 * 
	 * @param plan
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@Valid @ModelAttribute @RequestBody Plan plan,Model model) {
		planService.update(plan);
		return "success";
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) {
		planService.delete(id);
		return "success";
	}
	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Integer id,Model model) {
		if (id != -1) {
			model.addAttribute("plan", planService.get(id));
		}
	}

}
