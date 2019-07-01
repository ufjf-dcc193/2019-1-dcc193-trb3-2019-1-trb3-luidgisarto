package br.ufjf.dcc193.luidgisarto.trb3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("home")
public class HomeController {

    @GetMapping(value = {"", "index.html"})
    public ModelAndView getHome() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("index");

        return mv;
    }

    @GetMapping("/navbar")
    String getNavbar() {
        return "fragment/navbar";
    }
}
