package controller;

import domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.LogService;

import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;
    @RequestMapping("/all")
    public ModelAndView all(){
        ModelAndView modelAndView = new ModelAndView();
        List<Log> all = logService.all();
        modelAndView.getModelMap().addAttribute("logs",all);
        modelAndView.setViewName("log_list");
        return modelAndView;
    }
}
