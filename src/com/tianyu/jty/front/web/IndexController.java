package com.tianyu.jty.front.web;


import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tianyu.jty.acount.entity.AccountUser;
import com.tianyu.jty.acount.entity.Card;
import com.tianyu.jty.acount.entity.Trade;
import com.tianyu.jty.acount.service.AccountUserService;
import com.tianyu.jty.acount.service.CardService;
import com.tianyu.jty.acount.service.TradeService;

/**
 * 前台 controller
 * @author ty
 * @date 2015年1月14日
 */
@Controller
@RequestMapping(value = "")
public class IndexController{
	
	private List<Card> cards;
	@Autowired
	AccountUserService accountUserService;
	
	@Autowired
	CardService cardService;
	
	@Autowired
	TradeService tradeService;
	/**首页跳转*/
	@RequestMapping(value="index")
	public String list() {
		return "front/index";
	}
	/**用户注册跳转*/
	@RequestMapping(value="register")
	public String register() {
		return "front/register";
	}
	
	/**个人中心跳转，未登录用户不许查看*/
	@RequestMapping(value="center")
	public String center(HttpSession httpSession, Model model) {
		if(httpSession.getAttribute("accountUser")==null){
			model.addAttribute("tip","您未登录系统，请登录！");
			return "front/login";
		}
		return "front/center";
	}
	/*找回密码跳转*/
	@RequestMapping(value="findpw",method = RequestMethod.GET)
	public String findpw() {
		return "front/findpw";
	}
	
	/**交易记录查询跳转，未登录用户不许查看*/
	@RequestMapping(value="trade")
	public String trade(HttpSession httpSession, Model model) {
		if(httpSession.getAttribute("accountUser")==null){
			model.addAttribute("tip","您未登录系统，请登录！");
			return "front/login";
		}
			
		cards=cardService.findByUser((AccountUser) httpSession.getAttribute("accountUser"));
		model.addAttribute("cards",cards);
		return "front/trade";
	}
	/**交易记录查询中点击 账户跳转*/
	@RequestMapping(value="tradedetail")
	public String tradedetail(HttpSession httpSession,String id, Model model) {
		List<Trade> trades=tradeService.findByCard(id);
		cards=cardService.findByUser((AccountUser) httpSession.getAttribute("accountUser"));
		model.addAttribute("trades",trades);
		model.addAttribute("cards",cards);
		return "front/trade";
	}
	
	/**个人中心修改资料*/
	@RequestMapping(value="center",method = RequestMethod.POST)
	public String center(AccountUser accountUser, Model model,HttpSession httpSession) {
		AccountUser user=accountUserService.get(accountUser.getId());
		user.setEmail(accountUser.getEmail());
		user.setPhone(accountUser.getPhone());
		user.setGender(accountUser.getGender());
		user.setName(accountUser.getName());
		accountUserService.save(user);
		httpSession.setAttribute("accountUser", user);
		model.addAttribute("tip"," 修改成功！");
		return "front/center";
	}
	/**个人中心修改密码*/
	@RequestMapping(value="updatePw",method = RequestMethod.POST)
	public String updatePw(AccountUser accountUser, Model model,HttpSession httpSession) {
		AccountUser user=accountUserService.get(accountUser.getId());
		if(!user.getPassword().equals(accountUser.getPlainPassword())){
			model.addAttribute("tip"," 旧密码输入不正确！");
			return "front/center";
		}
		user.setPassword(accountUser.getPassword());
		model.addAttribute("tip"," 修改成功！");
		return "front/center";
	}
	/**个人中心找回密码*/
	@RequestMapping(value="findpw",method = RequestMethod.POST)
	public String findpw(String loginName, Model model,HttpSession httpSession) {
		AccountUser user=accountUserService.getUser(loginName);
		if(user!=null){
			try {
				MailUtil.sendEmail(user);
				model.addAttribute("tip"," 找回密码邮件已经发送到"+user.getEmail()+",请注意查收！");
			} catch (MessagingException e) {
				model.addAttribute("tip"," 邮件发送失败！");
			}
			
		}else{
			model.addAttribute("tip"," 用户名不正确找不到该用户！");
		}
		return "front/findpw";
	}
	/**注册表单提交*/
	@RequestMapping(value="doregister",method = RequestMethod.POST)
	public String register(AccountUser accountUser, Model model) {
		accountUserService.save(accountUser);
		model.addAttribute("tip"," 注册成功！");
		return "front/login";
	}
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value="login",method = RequestMethod.GET)
	public String login() {
		return "front/login";
	}

	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="loginout",method = RequestMethod.GET)
	public String loginout(HttpSession httpSession) {
		httpSession.invalidate();
		return "front/index";
	}

	/**
	 * 登录表单提交
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String fail(String loginName, String password, Model model,HttpSession httpSession) {
		AccountUser user=accountUserService.getUser(loginName, password);
		if(user==null){
			model.addAttribute("tip"," 用户名或密码不正确！");
			return "front/login";
		}else{
			httpSession.setAttribute("accountUser",user);
			return "front/index";
		}
	
	}

	/**
	 * 登出
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value="logout")
	public String logout(Model model,HttpSession httpSession) {
		httpSession.removeAttribute("accountUser");
		return "front/index";
	}
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
}
