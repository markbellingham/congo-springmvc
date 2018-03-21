package com.congo.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.congo.springmvc.model.CongoCustomers;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("msg", "Please Enter Your Login Details");
		return "login";
	}
	
    @RequestMapping(value="/login",method = RequestMethod.POST, params={"email","password"})
    public String submit(Model model, @ModelAttribute("CongoCustomers") CongoCustomers customer, String email, String password) {
        if (customer != null && customer.getEmail() != null & customer.getPassword() != null) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                model.addAttribute("msg", "Welcome" + customer.getFname() + " " + customer.getLname());
                return "logged-in";
            } else {
                model.addAttribute("error", "Invalid Details");
                return "login";
            }
        } else {
            model.addAttribute("error", "Please enter Details");
            return "login";
        }
    }

}
