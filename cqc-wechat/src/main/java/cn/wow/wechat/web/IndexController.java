package cn.wow.wechat.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.wow.common.service.ContactService;
import cn.wow.common.service.LinkService;
import cn.wow.common.service.MemberService;
import cn.wow.common.service.ScrollPicService;

/**
 * 首页控制器
 * @author zhenjunzhuo
 */
@Controller
@RequestMapping(value = "")
public class IndexController {
	
	@Autowired
	private ContactService contactService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ScrollPicService scrollPicService;
	@Autowired
	private LinkService linkService;
	
	//照片资源路径
  	@Value("${res.url.root}")
  	protected String resUrl;
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model) {

		return "index/index";
	}

}

		
		
