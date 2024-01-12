package com.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tunehub.entity.Playlist;
import com.tunehub.entity.Song;
import com.tunehub.service.PlaylistService;
import com.tunehub.service.SongService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class playlistController {
	@Autowired
	SongService songService;
	@Autowired
	PlaylistService playlistService;
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songList= songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		
		return "createPlaylist";
	}
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
	playlistService.addPlaylist(playlist);
	//updating 
	List<Song> songList = playlist.getSongs();
	for(Song s : songList) {
	
	s.getPlaylists().add(playlist);
	songService.updateSong(s);
	//update song objects in db
	}
		
		return "admin";
}
	@GetMapping("/viewPlaylist")
	public String viewPlaylists(Model model) {
		List<Playlist>allplaylists =playlistService.fetchAllPlaylists();
		model.addAttribute("allPlaylists",allplaylists);
		return "displayplaylists";
	}
	
	
	

}
