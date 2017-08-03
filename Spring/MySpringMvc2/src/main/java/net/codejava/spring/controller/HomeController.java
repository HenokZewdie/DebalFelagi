package net.codejava.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.spring.model.User;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/usered", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("userform", "usered", new User());
    }
 
    @RequestMapping(value = "/userforming", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("name", user.getUsername());
        model.addAttribute("state", user.getState());
        model.addAttribute("id", user.getId());
        return "userview";
    }
}