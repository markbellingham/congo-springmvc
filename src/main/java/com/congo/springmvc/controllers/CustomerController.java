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
		model.addAttribute("msg2", "Please enter your details to register");
		CongoCustomers customer = new CongoCustomers(); 
		model.addAttribute("CongoCustomers", customer);
		return "login";
	}
	
    @RequestMapping(value="/login",method = RequestMethod.POST, params={"email","password"})
    public String submit(Model model, HttpSession session, @ModelAttribute("CongoCustomers") CongoCustomers user) {
        if (user != null && user.getEmail() != null && user.getPassword() != null) {
        	CongoCustomers customer = cdao.findCustomerByEmail(user.getEmail());
            if (user.getPassword().equals(customer.getPassword())) {
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
    
    @RequestMapping(value="/login",method = RequestMethod.POST, params={"fname","lname","address1","city","postcode","phone","email","password"})
    public String register(Model model, HttpSession session, @ModelAttribute("CongoCustomers") CongoCustomers user) {
    	
    	// First check the email address does not already exist in the system
    	if (CongoCustomers.checkEmail(user.getEmail())) {
    		model.addAttribute("error", "Sorry, that email address is already registered. Did you forget your password?");
    		return "login";
    	}
    	
    	// Then register the user and log them in
    	Boolean result = cdao.insertNewCustomer(user);
    	if (result) {
    		model.addAttribute("customer", user);
    		session.setAttribute("customer", user);
    		return "logged-in";
    	} else {
    		model.addAttribute("error", "Sorry there was an error.");
    		return "login";
    	}
    }
}
