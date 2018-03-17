package com.congo.springmvc.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.congo.springmvc.dao.MusicDAO;
import com.congo.springmvc.model.MusicRecordings;

@Controller
public class ListController {
	
	@Autowired
	private MusicDAO mdao = MusicDAO.getInstance();
	
	ArrayList<Integer> orderArray = new ArrayList<Integer>();
	
	@RequestMapping(value="/albums")
	public String listAlbums(Model model) {
		model.addAttribute("albums", this.mdao.findAllRecordings());
		return "albums";
	}
	
	@RequestMapping(value="/albums/{recordingId}")
	public String listAlbum(Model model, @PathVariable("recordingId") int recordingId) {
		model.addAttribute("album", this.mdao.findAlbumById(recordingId));
		model.addAttribute("tracks", this.mdao.findAlbumTracks(recordingId));
		return "album";
	}
	
	@RequestMapping(value="/categories")
	public String listCategories(Model model) {
		model.addAttribute("categories", this.mdao.findAllCategories());
		model.addAttribute("albums", this.mdao.findAllRecordings());
		return "categories";
	}
	
	@RequestMapping(value="/categories/{category}")
	public String listAlbumsByCategory(Model model, @PathVariable("category") String category) {
//		category = category.toLowerCase();
		model.addAttribute("categories", this.mdao.findAllCategories());
		model.addAttribute("albums", this.mdao.findRecordingsByCategory(category));
		return "categories";
	}
	
	@RequestMapping(value="/categories",method=RequestMethod.GET,params={"category"})
	public String listAlbumsByCategory2(Model model, String category) {
		model.addAttribute("categories", this.mdao.findAllCategories());
		model.addAttribute("albums", this.mdao.findRecordingsByCategory(category));
		return "categories";
	}
	
	@RequestMapping(value="/price-picker")
	public String listPrices() {
		return "price-picker";
	}
	
	@RequestMapping(value="/price-picker",method=RequestMethod.GET,params={"price"})
	public String listAlbumsByPrice(Model model, int price) {
		model.addAttribute("albums", this.mdao.findRecordingsByPrice(price));
		return "price-picker";
	}
	
	@RequestMapping(value="/artist-finder")
	public String searchArtists() {
		return "artist-finder";
	}
	
	@RequestMapping(value="/artist/{artistName}")
	public String searchArtist(Model model, @PathVariable("artistName") String name) {
		name = name.toLowerCase();
		model.addAttribute("albums", this.mdao.findRecordingsByArtist(name));
		return "artist-finder";
	}
	
	@RequestMapping(value="/artist-finder", method=RequestMethod.POST,params= {"name"})
	public String listArtists(Model model, String name) {
		name = name.toLowerCase();
		model.addAttribute("albums", this.mdao.findRecordingsByArtist(name));
		return "artist-finder";
	}
	
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
		System.out.println(session.getAttribute("myOrder"));
		return getPreviousPageByRequest(request).orElse("/"); //else go to home page
	}
	
	@RequestMapping(value="/show-order")
	public String showOrder(Model model, HttpServletRequest request, HttpSession session) {
		ArrayList<MusicRecordings> albumsInOrder = new ArrayList<MusicRecordings>();
		session = request.getSession();
		if (session.getAttribute("myOrder") != null) {
			orderArray = (ArrayList<Integer>) session.getAttribute("myOrder");
			albumsInOrder = mdao.findAlbumsInOrder(orderArray);
			System.out.println("albumArray = " + albumsInOrder);
			model.addAttribute("order", albumsInOrder);
		}
		return "show-order";
	}
	
	/**
	* Returns the viewName to return for coming back to the sender url
	*
	* @param request Instance of {@link HttpServletRequest} or use an injected instance
	* @return Optional with the view name. Recomended to use an alternativa url with
	* {@link Optional#orElse(java.lang.Object)}
	*/
	protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
	{
	   return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
	}

}
