package controller;

import domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.PermissionService;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/all")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissions = permissionService.findAll();
        modelAndView.getModelMap().addAttribute("permissions",permissions);
        modelAndView.setViewName("permission_list");
        return modelAndView;
    }
    @RequestMapping("/add")
    public String add(Permission permission){
        permissionService.add(permission);
        return "redirect:/permission/all";
    }
}
