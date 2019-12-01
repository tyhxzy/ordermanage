package controller;

import domain.Role;
import domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.RoleService;
import service.UserService;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/all")
//    @Secured({"ROLE_ADMIN"})
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> users = userService.findAll();
        modelAndView.getModelMap().addAttribute("users",users);
        modelAndView.setViewName("user_list");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(UserInfo userInfo) {
        userService.add(userInfo);
        return "redirect:/user/all";
    }

    @RequestMapping("/info")
    @RolesAllowed({"ROLE_ADMIN"})
    public ModelAndView findById(String id) {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo user = userService.findById(id);
        modelAndView.getModelMap().addAttribute("user",user);
        modelAndView.setViewName("user_show");
        return modelAndView;
    }

    @RequestMapping("/findOtherRole")
//    @PermitAll
//    @DenyAll
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ModelAndView findOtherRole(String id){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = roleService.findOtherByUserId(id);
        modelAndView.getModelMap().addAttribute("otherRoles",roles);
        modelAndView.getModelMap().addAttribute("id",id);
        modelAndView.setViewName("user_role_add");
        return modelAndView;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(String userId,String[] ids){
        userService.addUserRole(userId,ids);
        return "redirect:/user/all";
    }
}
