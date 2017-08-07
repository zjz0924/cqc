package cn.wow.support.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wow.common.domain.Account;
import cn.wow.common.domain.Menu;
import cn.wow.common.service.AccountService;
import cn.wow.common.service.MenuService;
import cn.wow.support.utils.Contants;

/**
 * 首页控制器
 * @author zhenjunzhuo
 */
@Controller
@RequestMapping(value = "")
public class IndexController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model) {
		Account currentAccount = (Account) request.getSession().getAttribute(Contants.CURRENT_ACCOUNT);
		
		List<Menu> menuList = menuService.getMenuList();
		
		model.addAttribute("currentAccount", currentAccount);
		model.addAttribute("menuList", menuList);
		return "index/index";
	}

	
	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request, Model model) {
		Account currentAccount = (Account) request.getSession().getAttribute(Contants.CURRENT_ACCOUNT);
		
		return "index/main";
	}
	
	
}

		
		
