package com.congo.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.congo.springmvc.dao.MusicDAO;

@Controller
public class ListController {
	
	@Autowired
	private MusicDAO mdao;
	
	@RequestMapping(value="/albums")
	public String listAlbums(Model model) {
		model.addAttribute("albums", this.mdao.findAllRecordings());
		return "albums";
	}
	
	@RequestMapping(value="/categories")
	public String listCategories(Model model) {
		model.addAttribute("categories", this.mdao.findAllCategories());
		model.addAttribute("albums", this.mdao.findAllRecordings());
		return "categories";
	}
	
	@RequestMapping(value="/categories",method=RequestMethod.GET,params={"category"})
	public String listAlbumsByCategory(Model model, String category) {
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
		float fprice = (float) price;
		
		model.addAttribute("price", this.mdao.findAllCategories());
		model.addAttribute("albums", this.mdao.findRecordingsByPrice(fprice));
		return "price-picker";
	}

}
