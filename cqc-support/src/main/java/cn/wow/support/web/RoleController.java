package cn.wow.support.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wow.common.domain.Account;
import cn.wow.common.domain.Menu;
import cn.wow.common.domain.Role;
import cn.wow.common.domain.RolePermission;
import cn.wow.common.service.AccountService;
import cn.wow.common.service.MenuService;
import cn.wow.common.service.RolePermissionService;
import cn.wow.common.service.RoleService;
import cn.wow.common.utils.AjaxVO;
import cn.wow.common.utils.pagination.PageMap;
import cn.wow.support.utils.Contants;

@Controller
@RequestMapping(value = "role")
public class RoleController extends AbstractController {

	private static Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/list")
	public String list(HttpServletRequest httpServletRequest, Model model) {
		List<Menu> menuList = menuService.getMenuList();

		model.addAttribute("menuList", menuList);
		return "sys/role_list";
	}

	@RequestMapping(value = "/data")
	@ResponseBody
	public AjaxVO data(HttpServletRequest httpServletRequest, Model model, String roleName) {
		AjaxVO vo = new AjaxVO();

		try {
			Map<String, Object> map = new PageMap(false);
			map.put("custom_order_sql", "name asc");
			if (StringUtils.isNotBlank(roleName)) {
				map.put("name", roleName);
			}
			List<Role> dataList = roleService.selectAllList(map);

			vo.setSuccess(true);
			vo.setData(dataList);
		} catch (Exception ex) {
			vo.setSuccess(false);
			vo.setMsg("系统异常，数据加载失败");
		}
		return vo;
	}

	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, String id, String name) {
		String resultCode = "";
		String resultMsg = "";
		Role role = null;
		List<Role> roleList = getRoleListByName(name);

		try {
			if (StringUtils.isNotBlank(id)) {
				if (roleList != null && roleList.size() > 0
						&& roleList.get(0).getId().longValue() != Long.parseLong(id)) {
					resultCode = Contants.EDIT_FAIL;
					resultMsg = "角色名已存在";
				}else{
					role = roleService.selectOne(Long.parseLong(id));
					role.setName(name);
					roleService.update(role);

					resultCode = Contants.EDIT_SUCCESS;
					resultMsg = Contants.EDIT_SUCCESS_MSG;
				}
			} else {
				if (roleList != null && roleList.size() > 0){
					resultCode = Contants.SAVE_FAIL;
					resultMsg = "角色名已存在";
				}else{
					role = new Role();
					role.setName(name);
					roleService.save(role);

					resultCode = Contants.SAVE_SUCCESS;
					resultMsg = Contants.SAVE_SUCCESS_MSG;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			resultCode = Contants.EXCEPTION;
			resultMsg = Contants.EXCEPTION_MSG;
		}

		model.addAttribute("resultCode", resultCode);
		model.addAttribute("resultMsg", resultMsg);
		model.addAttribute("facadeBean", role);
		return "role_detail";
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public AjaxVO delete(HttpServletRequest request, String id) {
		AjaxVO vo = new AjaxVO();

		if (StringUtils.isNotBlank(id)) {
			int num = roleService.deleteByPrimaryKey(Long.parseLong(id));

			if (num > 0) {
				getResponse(vo, Contants.SUC_DELETE);
			} else {
				getResponse(vo, Contants.FAIL_DELETE);
			}
		} else {
			getResponse(vo, Contants.FAIL_DELETE);
		}

		return vo;
	}

	@ResponseBody
	@RequestMapping(value = "/rolePermission")
	public AjaxVO rolePermission(HttpServletRequest request, String id) {
		AjaxVO vo = new AjaxVO();

		if (StringUtils.isNotBlank(id)) {
			RolePermission permission = rolePermissionService.selectOne(Long.parseLong(id));

			vo.setData(permission);
			vo.setSuccess(true);
		} else {
			vo.setSuccess(false);
			vo.setMsg("系统异常，获取角色权限失败");
		}
		return vo;
	}

	@ResponseBody
	@RequestMapping(value = "/addRole")
	public AjaxVO addRole(HttpServletRequest request, String id, String name, String permission) {
		AjaxVO vo = new AjaxVO();
		vo.setMsg("保存成功");
		List<Role> roleList = getRoleListByName(name);

		try {
			RolePermission rolePermission = null;
			Role role = null;

			if (StringUtils.isNotBlank(id)) {
				if (roleList != null && roleList.size() > 0
						&& roleList.get(0).getId().longValue() != Long.parseLong(id)) {
					vo.setSuccess(false);
					vo.setMsg("角色名已存在");
				}else{
					role = roleService.selectOne(Long.parseLong(id));
					role.setName(name);

					rolePermission = rolePermissionService.selectOne(Long.parseLong(id));
					if (rolePermission == null) {
						rolePermission = new RolePermission();
						rolePermission.setRoleId(role.getId());
					}
					rolePermission.setPermission(permission);

					roleService.updateRole(role, rolePermission);
				}
			} else {
				if (roleList != null && roleList.size() > 0){
					vo.setSuccess(false);
					vo.setMsg("角色名已存在");
				}else{
					role = new Role(name);
					rolePermission = new RolePermission(permission);

					roleService.addRole(role, rolePermission);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			vo.setMsg("系统异常，保存失败");
			vo.setSuccess(false);
		}
		return vo;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteRole")
	public AjaxVO deleteRole(HttpServletRequest request, Long roleId) {
		AjaxVO vo = new AjaxVO();
		vo.setMsg("删除失败");

		try {
			Map<String, Object> map = new PageMap(false);
			map.put("roleId", roleId);
			List<Account> accountList = accountService.selectAllList(map);

			if (accountList == null || accountList.size() < 1) {
				roleService.deleteRole(roleId);
			} else {
				vo.setSuccess(false);
				vo.setMsg("删除失败，当前角色正在使用中");
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			vo.setSuccess(false);
			vo.setMsg("系统异常，无法删除");
		}
		return vo;
	}

	public List<Role> getRoleListByName(String name) {
		Map<String, Object> map = new PageMap(false);
		map.put("name", name);
		List<Role> roleList = roleService.selectAllList(map);
		return roleList;
	}

}