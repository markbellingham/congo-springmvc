package com.congo.springmvc.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.congo.springmvc.dao.CustomersDAO;
import com.congo.springmvc.dao.MusicDAO;
import com.congo.springmvc.model.CongoCustomers;
import com.congo.springmvc.model.MusicRecordings;
import com.congo.springmvc.model.OrderDetails;

@Controller
@RequestMapping("/order")
public class OrdersController {
	
	@Autowired
	private MusicDAO mdao = MusicDAO.getInstance();
	private CustomersDAO cdao = CustomersDAO.getInstance();
	
	ArrayList<Integer> orderArray = new ArrayList<Integer>();
	
	@RequestMapping(value="/add-to-order/{recordingId}")
	public String addToOrder(HttpServletRequest request, @PathVariable("recordingId") int recordingId, HttpSession session)  {
		session = request.getSession();
		if(session.getAttribute("myOrder") != null) {
			orderArray = (ArrayList<Integer>) session.getAttribute("myOrder");
		} else {
			orderArray = new ArrayList<Integer>();
		}
		orderArray.add(recordingId);
		session.setAttribute("myOrder", orderArray);
		return getPreviousPageByRequest(request).orElse("/"); //else go to home page
	}
	
	@RequestMapping(value="/basket")
	public String showOrder(Model model, HttpServletRequest request, HttpSession session) {
		ArrayList<MusicRecordings> albumsInOrder = new ArrayList<MusicRecordings>();
		session = request.getSession();
		if (session.getAttribute("myOrder") != null) {
			orderArray = (ArrayList<Integer>) session.getAttribute("myOrder");
			albumsInOrder = mdao.findAlbumsInOrder(orderArray);
			float grandTotal = calculateGrandTotal(albumsInOrder);
			model.addAttribute("order", albumsInOrder);
			model.addAttribute("grandTotal", grandTotal);
		}
		return "show-order";
	}
	
	@RequestMapping(value="/update-order/{recordingId}",method=RequestMethod.POST,params={"quantity"})
	public String updateOrder(Model model, HttpServletRequest request, HttpSession session, 
			@PathVariable("recordingId") int recordingId, int quantity) {
		ArrayList<MusicRecordings> albumsInOrder = new ArrayList<MusicRecordings>();
		session = request.getSession();
		if (session.getAttribute("myOrder") != null) {
			orderArray = (ArrayList<Integer>) session.getAttribute("myOrder");
			orderArray.removeAll(Collections.singleton(recordingId));
			for(int i = 1; i <= quantity; i++) {
				orderArray.add(recordingId);
			}
			session.setAttribute("myOrder", orderArray);
			albumsInOrder = mdao.findAlbumsInOrder(orderArray);
			float grandTotal = calculateGrandTotal(albumsInOrder);
			model.addAttribute("order", albumsInOrder);
			model.addAttribute("grandTotal", grandTotal);
		}
		return "show-order";
	}
	
	@RequestMapping(value="/delete-from-order/{recordingId}",method=RequestMethod.POST)
	public String deleteFromOrder(Model model, HttpServletRequest request, HttpSession session, @PathVariable("recordingId") int recordingId) {
		ArrayList<MusicRecordings> albumsInOrder = new ArrayList<MusicRecordings>();
		session = request.getSession();
		if(session.getAttribute("myOrder") != null) {
			orderArray = (ArrayList<Integer>) session.getAttribute("myOrder");
			orderArray.removeAll(Collections.singleton(recordingId));
			session.setAttribute("myOrder", orderArray);
			albumsInOrder = mdao.findAlbumsInOrder(orderArray);
			float grandTotal = calculateGrandTotal(albumsInOrder);
			model.addAttribute("order", albumsInOrder);
			model.addAttribute("grandTotal", grandTotal);
		}
		return "show-order";
	}
	
    @RequestMapping(value="/show-all-my-orders")
    public String showAllOrders(Model model, HttpSession session, @ModelAttribute("CongoCustomers") CongoCustomers user) {
    	user = (CongoCustomers) session.getAttribute("customer");
    	if (user != null && user.isLoggedIn() == true) {
    		int custId = user.getCustId();
    		ArrayList<OrderDetails> orders = cdao.findAllMyOrders(custId);
    		model.addAttribute("orders", orders);
    		return "show-all-my-orders";
    	} else {
    		model.addAttribute("error", "You need to log in first.");
    		return "login";
    	}
    	
    }
	
	/**
	* Returns the viewName to return for coming back to the sender URL
	*
	* @param request Instance of {@link HttpServletRequest} or use an injected instance
	* @return Optional with the view name. Recommended to use an alternative URL with
	* {@link Optional#orElse(java.lang.Object)}
	*/
	protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
	{
	   return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
	}
	
	protected float calculateGrandTotal(ArrayList<MusicRecordings> albumsInOrder) {
		float grandTotal = 0;
		for(MusicRecordings albums : albumsInOrder) {
			grandTotal += albums.getTotalPrice();
		}
		return grandTotal;
	}
	
}
