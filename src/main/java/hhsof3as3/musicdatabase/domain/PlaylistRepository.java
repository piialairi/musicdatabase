package hhsof3as3.musicdatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlist, Long>{
	//List<Playlist> findByName (String name);
	List<Playlist> findByArtist (String artist);
}
