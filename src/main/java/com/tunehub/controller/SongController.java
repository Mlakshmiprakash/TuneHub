package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tunehub.entity.Song;
import com.tunehub.service.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class SongController {
	@Autowired
	SongService service;
	@PostMapping("/addsong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus = service.songExists(song.getName());
		if(songStatus == false) {
			service.addSong(song);
			System.out.println("Song added succesfully");
		}else {
			System.out.println("Song Exists already");
		}
		return "admin";
	}
	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		List<Song> songsList = service.fetchAllSongs();
		model.addAttribute("songs" ,songsList);

		return "display";
	
	}
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		boolean premiumUser=false;
		if(premiumUser == true) {
			List<Song> songList= service.fetchAllSongs();
			model.addAttribute("songs", songList);
			return "display";
		}
		else {
		return "makePayment";
	}
	
	}

}


