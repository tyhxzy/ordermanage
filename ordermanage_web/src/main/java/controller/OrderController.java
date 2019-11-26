package controller;

import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/all")
    private ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> all = orderService.findAll();
        modelAndView.getModelMap().addAttribute("orders",all);
        modelAndView.setViewName("orders_list");
        return modelAndView;
    }
}
