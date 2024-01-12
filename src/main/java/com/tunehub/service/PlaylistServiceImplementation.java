package com.tunehub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tunehub.entity.Playlist;
import com.tunehub.entity.Song;
import com.tunehub.repository.PlaylistRepository;

@Service
public class PlaylistServiceImplementation  implements PlaylistService{
	@Autowired
PlaylistRepository repo;
	@Override
	public void addPlaylist(Playlist playlist) {
		repo.save(playlist);
		
		
	}
	@Override
	public List<Playlist> fetchAllPlaylists() {
		
		return repo.findAll();
	}

}
