package com.congo.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.congo.springmvc.dao.*;

@Controller
public class ListController {
	
	@Autowired
	private MusicRecordingsDAO mrdao;
	private MusicCategoriesDAO mcdao;
	
	@RequestMapping("/albums")
	public String listAlbums(Model model) {
		model.addAttribute("albums", this.mrdao.findAllRecordings());
		return "albums";
	}
	
	@RequestMapping("/categories")
	public String listCategories (Model model) {
		model.addAttribute("categories", this.mcdao.findAllCategories());
		return "categories";
	}
	
	
}
