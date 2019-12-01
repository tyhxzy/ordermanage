package controller;

import domain.Permission;
import domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.PermissionService;
import service.RoleService;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/all")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = roleService.findAll();
        modelAndView.getModelMap().addAttribute("roles",roles);
        modelAndView.setViewName("role_list");
        return modelAndView;
    }
    @RequestMapping("/add")
    public String add(Role role){
        roleService.add(role);
        return "redirect:/role/all";
    }

    @RequestMapping("/findOtherPermission")
    public  ModelAndView findOtherPermission(String roleId){
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissions = permissionService.findOtherPermissionByRoleId(roleId);
        modelAndView.getModelMap().addAttribute("permissions",permissions);
        modelAndView.getModelMap().addAttribute("roleId",roleId);
        modelAndView.setViewName("role_permission_add");
        return modelAndView;

    }
    @RequestMapping("/addRolePermission")
    public String addRolePermission(String roleId,String[] permissions){
        roleService.addRolePermission(roleId,permissions);
        return "redirect:/role/all";
    }

}
