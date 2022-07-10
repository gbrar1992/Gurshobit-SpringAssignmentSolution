package com.gurshobit.collegefestival.controllers;

import com.gurshobit.collegefestival.entities.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class HomeController {
    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("allStudents",new ArrayList<Student>());
        return "redirect:/students/list";
    }

    @RequestMapping(value = "/403")
    public ModelAndView accessDenied(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/404")
    public ModelAndView pageNotFound(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", Page your looking for doesn't exists!");
        } else {
            model.addObject("msg",
                    "Page your looking for doesn't exists!");
        }

        model.setViewName("404");
        return model;

    }
}
