package controller;

import com.github.pagehelper.PageInfo;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/all")
    private ModelAndView findAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize){
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Orders> all = orderService.findAll(pageNum,pageSize);
        modelAndView.getModelMap().addAttribute("orders",all);
        modelAndView.setViewName("orders_list");
        return modelAndView;
    }
    @RequestMapping("/info")
    private ModelAndView findDesc(String id){
        ModelAndView modelAndView = new ModelAndView();
        Orders order = orderService.findDesc(id);
        modelAndView.getModelMap().addAttribute("orders",order);
        modelAndView.setViewName("orders_show");
        return modelAndView;
    }
}
