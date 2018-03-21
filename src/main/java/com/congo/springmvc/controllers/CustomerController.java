package com.congo.springmvc.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.congo.springmvc.dao.CustomersDAO;
import com.congo.springmvc.model.CongoCustomers;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomersDAO cdao = CustomersDAO.getInstance();
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("msg", "Please Enter Your Login Details");
		return "login";
	}
	
    @RequestMapping(value="/login",method = RequestMethod.POST, params={"email","password"})
    public String submit(Model model, HttpSession session, @ModelAttribute("CongoCustomers") CongoCustomers customer, String email, String password) {
    	customer = cdao.findCustomerByEmail(email);
        if (customer != null && customer.getEmail() != null & customer.getPassword() != null) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {            	
                model.addAttribute("customer", customer);
                session.setAttribute("customer", customer);
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
